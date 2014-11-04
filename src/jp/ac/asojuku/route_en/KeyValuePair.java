package jp.ac.asojuku.route_en;

import android.util.Pair;

public class KeyValuePair extends Pair<String,String> {

    public KeyValuePair(String key, String value) {
        super(key, value);
    }

    public String getKey(){
        return super.first;
    }

    public String getValue(){
        return super.second;
    }
}
