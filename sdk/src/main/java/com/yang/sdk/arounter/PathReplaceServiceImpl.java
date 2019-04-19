package com.yang.sdk.arounter;

import android.content.Context;
import android.net.Uri;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe: ARouter路由找不到路径替换处理
 * Created by Yang on 2019/4/17.
 */
@Route(path = PathReplaceServiceImpl.PATH)
public class PathReplaceServiceImpl implements PathReplaceService {


    static final String PATH = "/service/PathReplaceServiceImpl";
    private Map<String, String> pathMap;

    @Override
    public String forString(String path) {
        return pathMap.containsKey(path) ? pathMap.get(path) : path;
    }

    @Override
    public Uri forUri(Uri uri) {
        return uri;
    }

    @Override
    public void init(Context context) {
        pathMap = new HashMap<>();
    }

    public void replacePath(String sourcePath, String targetPath) {
        pathMap.put(sourcePath, targetPath);
    }

    public Map<String, String> getReplacePathMap() {
        return Collections.unmodifiableMap(pathMap);
    }
}
