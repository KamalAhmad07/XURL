package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class XUrlImpl implements XUrl{


    String shortUrl= "http://short.url/";  
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
    private StringBuilder s= new StringBuilder(9);
    Map<String,String> map = new HashMap<>();
    Map<String,Integer> hitCount = new HashMap<>();
       
    @Override
    public String registerNewUrl(String longUrl) {
        s.setLength(0);
        if(map.containsKey(longUrl)){
             return map.get(longUrl);
        } else{       
             for(int  i=0;i<9;i++){
                int  x = (int) (alphabet.length() * Math.random());
                s.append(alphabet.charAt(x));
             }
          }
          map.put(longUrl, shortUrl+s.toString());
          hitCount.put(longUrl,0);
        
        return   shortUrl+ s.toString();
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl){                        
            if(map.containsValue(shortUrl)){
                return null;                            
            }else{
               map.put(longUrl,shortUrl);
               hitCount.put(longUrl,0);
            }
        return map.get(longUrl);
    }

    @Override
    public String getUrl(String shortUrl) {
                  
           for(Entry<String,String> entry:map.entrySet()) {
                 if(entry.getValue().equals(shortUrl)){
                     hitCount.put(entry.getKey(),hitCount.getOrDefault(entry.getKey(),0)+1);
                     return entry.getKey();
                    }
           }
        return null; 
    }

    @Override
    public Integer getHitCount(String longUrl) {
          if(hitCount.containsKey(longUrl)){
              return hitCount.get(longUrl);
          }
        return 0;
    }

    @Override
    public String delete(String longUrl) {
        map.remove(longUrl);
        return longUrl;
    }

}