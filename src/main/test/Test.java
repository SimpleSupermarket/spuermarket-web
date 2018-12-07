import net.sourceforge.pinyin4j.PinyinHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.UUID;

/**
 * 
 * @author 董文强
 * @date 2018年12月07日
 * @version 1.0
 */
public class Test {
 private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        String[] strings = PinyinHelper.toGwoyeuRomatzyhStringArray('系');
        System.out.println(Arrays.toString(strings));
        strings =  PinyinHelper.toTongyongPinyinStringArray('系');
        System.out.println(Arrays.toString(strings));
        strings =  PinyinHelper.toMPS2PinyinStringArray('系');
        System.out.println(Arrays.toString(strings));
        strings =  PinyinHelper.toHanyuPinyinStringArray('系');
        System.out.println(Arrays.toString(strings));
        strings =  PinyinHelper.toYalePinyinStringArray('系');
        System.out.println(Arrays.toString(strings));
        strings =  PinyinHelper.toWadeGilesPinyinStringArray('系');
        System.out.println(Arrays.toString(strings));
        strings =  PinyinHelper.toTongyongPinyinStringArray('系');
        System.out.println(Arrays.toString(strings));

        String uuid = UUID.randomUUID().toString();
      //  new
        byte[] bytes = {1,2,3,4,5,6,7,8,9,0};
        uuid = UUID.nameUUIDFromBytes(bytes).toString();
       // UUID.randomUUID()
        System.out.println(uuid);
    }
}
