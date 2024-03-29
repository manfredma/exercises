package manfred.end.es.client.java;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.term.TermSuggestion;

public class SuggestDemo {
    
    private static Logger logger = LogManager.getRootLogger();  
    
    //拼写检查
    public static void termSuggest(TransportClient client) {
            
        // 1、创建search请求
        //SearchRequest searchRequest = new SearchRequest();
        SearchRequest searchRequest = new SearchRequest("mess"); 
        
        // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
         
        sourceBuilder.size(0); 
        
        //做查询建议        
        //词项建议
        SuggestionBuilder termSuggestionBuilder =
                SuggestBuilders.termSuggestion("user").text("kmichy"); 
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("suggest_user", termSuggestionBuilder);     
        sourceBuilder.suggest(suggestBuilder);
        
        searchRequest.source(sourceBuilder);    

        try{
            //3、发送请求        
            SearchResponse searchResponse = client.search(searchRequest).get();
            
            
            //4、处理响应
            //搜索结果状态信息
            if(RestStatus.OK.equals(searchResponse.status())) {
                // 获取建议结果
                Suggest suggest = searchResponse.getSuggest(); 
                TermSuggestion termSuggestion = suggest.getSuggestion("suggest_user"); 
                for (TermSuggestion.Entry entry : termSuggestion.getEntries()) { 
                    logger.info("text: " + entry.getText().string());
                    for (TermSuggestion.Entry.Option option : entry) { 
                        String suggestText = option.getText().string();
                        logger.info("   suggest option : " + suggestText);
                    }
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            logger.error(e);
        }
            /*
              "suggest": {
                "my-suggestion": [
                  {
                    "text": "tring",
                    "offset": 0,
                    "length": 5,
                    "options": [
                      {
                        "text": "trying",
                        "score": 0.8,
                        "freq": 1
                      }
                    ]
                  },
                  {
                    "text": "out",
                    "offset": 6,
                    "length": 3,
                    "options": []
                  },
                  {
                    "text": "elasticsearch",
                    "offset": 10,
                    "length": 13,
                    "options": []
                  }
                ]
              }*/

    }
    //自动补全
    public static void completionSuggester(TransportClient client) {
                
        // 1、创建search请求
        //SearchRequest searchRequest = new SearchRequest();
        SearchRequest searchRequest = new SearchRequest("music"); 
        
        // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
         
        sourceBuilder.size(0); 
        
        //做查询建议        
        //自动补全
        /*POST music/_search?pretty
                {
                    "suggest": {
                        "song-suggest" : {
                            "prefix" : "lucene s", 
                            "completion" : { 
                                "field" : "suggest" ,
                                "skip_duplicates": true
                            }
                        }
                    }
                }*/

        SuggestionBuilder termSuggestionBuilder =
                SuggestBuilders.completionSuggestion("suggest").prefix("lucene s")
                .skipDuplicates(true); 
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion("song-suggest", termSuggestionBuilder);     
        sourceBuilder.suggest(suggestBuilder);
        
        searchRequest.source(sourceBuilder);    
            
        try {
            //3、发送请求        
            SearchResponse searchResponse = client.search(searchRequest).get();
            
            
            //4、处理响应
            //搜索结果状态信息
            if(RestStatus.OK.equals(searchResponse.status())) {
                // 获取建议结果
                Suggest suggest = searchResponse.getSuggest(); 
                CompletionSuggestion termSuggestion = suggest.getSuggestion("song-suggest"); 
                for (CompletionSuggestion.Entry entry : termSuggestion.getEntries()) { 
                    logger.info("text: " + entry.getText().string());
                    for (CompletionSuggestion.Entry.Option option : entry) { 
                        String suggestText = option.getText().string();
                        logger.info("   suggest option : " + suggestText);
                    }
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        try (TransportClient client = InitDemo.getClient();) {
            termSuggest(client);
            
            logger.info("--------------------------------------");
            
            completionSuggester(client);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}