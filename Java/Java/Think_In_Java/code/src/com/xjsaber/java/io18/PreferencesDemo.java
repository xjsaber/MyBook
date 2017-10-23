package com.xjsaber.java.io18;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
/**
 * @author xjsaber
 */
public class PreferencesDemo {

    public static void main(String[] args) throws BackingStoreException {
        // 创建了节点，用它来加载或者读取数据。向节点载入了各种不同类型的数据项，然后获取其keys()。
        // 以String[]的形式返回，如果习惯于keys()属于集合类库
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        prefs.put("Location", "Oz");
        prefs.put("Footwear", "Ruby Slippers");
        prefs.putInt("Companions", 4);
        prefs.putBoolean("Are there witches", true);
        int usageCount = prefs.getInt("UsageCount", 0);
        usageCount++;
        prefs.putInt("UsageCount", usageCount);
        for (String key : prefs.keys()){
            System.out.println(key + ": " + prefs.get(key, null));
        }
        // You must always provide a default value:
        System.out.println("How many companions does Dorothy have? " + prefs.getInt("Companions", 0));
    }
}
