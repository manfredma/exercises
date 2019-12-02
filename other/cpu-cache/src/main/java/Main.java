import fr.ujm.tse.lt2c.satin.cache.exceptions.CacheNotFoundException;
import fr.ujm.tse.lt2c.satin.cache.size.CacheInfo;
import fr.ujm.tse.lt2c.satin.cache.size.CacheLevel;
import fr.ujm.tse.lt2c.satin.cache.size.CacheLevelInfo;
import fr.ujm.tse.lt2c.satin.cache.size.CacheType;

/**
 * 查看 cpu 信息 => sysctl machdep.cpu
 *
 * @author manfred
 * @since 2019-12-02 下午2:19
 */
public class Main {
    public static void main(String[] args) throws CacheNotFoundException {
        CacheInfo info = CacheInfo.getInstance();
        CacheLevelInfo l1Datainf = info.getCacheInformation(CacheLevel.L1, CacheType.DATA_CACHE);
        System.out.println("第一级数据缓存信息："+l1Datainf.toString());

        CacheLevelInfo l1Instrinf = info.getCacheInformation(CacheLevel.L1, CacheType.INSTRUCTION_CACHE);
        System.out.println("第一级指令缓存信息："+l1Instrinf.toString());
    }
}
