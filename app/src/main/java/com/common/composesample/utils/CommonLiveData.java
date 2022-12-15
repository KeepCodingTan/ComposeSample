package com.common.composesample.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Sun
 * @CreateDate: 2022/7/27
 * @Description: java类作用描述
 */
public final class CommonLiveData {

    private static CommonLiveData instance;
    private Map<String,LiveData<Object>> liveDataMap;

    public static CommonLiveData get(){
        if(instance == null){
            synchronized (CommonLiveData.class){
                if(instance == null){
                    instance = new CommonLiveData();
                }
            }
        }
        return instance;
    }

    private CommonLiveData(){
        liveDataMap = new HashMap<>();
    }

    public <T> MutableLiveData<T> with(String target,Class<T> type){
        if(!liveDataMap.containsKey(target)){
            liveDataMap.put(target,new MutableLiveData<>());
        }
        return (MutableLiveData<T>) liveDataMap.get(target);
    }

    /**
     * 防止数据倒灌的livedata
     * @param target
     * @param type
     * @param <T>
     * @return
     */
    public <T> UnPeekLiveData<T> withUnPeek(String target,Class<T> type){
        if(!liveDataMap.containsKey(target)){
            liveDataMap.put(target,new UnPeekLiveData<>());
        }
        return (UnPeekLiveData<T>) liveDataMap.get(target);
    }

}
