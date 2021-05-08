/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.jdbc.mybatis;

import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

/**
 * A Lego Set consisting of multiple Blocks and a manual
 *
 * @author Jens Schauder
 */
public class LegoSet {

    private Map<String, Model> models = new HashMap<>();

    private @Id
    Integer id;
    private String name;
    private Manual manual;

    public void addModel(String name, String description) {

        Model model = new Model();
        model.name = name;
        model.description = description;
        models.put(name, model);
    }

    public Map<String, Model> getModels() {
        return models;
    }

    public void setModels(Map<String, Model> models) {
        this.models = models;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manual getManual() {
        return manual;
    }

    public void setManual(Manual manual) {
        this.manual = manual;
    }
}
