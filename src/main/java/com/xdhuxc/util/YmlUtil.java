/**
 * 
 */
package com.xdhuxc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.yaml.snakeyaml.Yaml;

/**
 * @author zhukai
 *
 */
public class YmlUtil {
	
	
	private static Object genYamlObjFormPath(File yml_file) {
		Yaml yaml = new Yaml();
		Object obj = null;
		InputStream in = null;
		try {
			in = new FileInputStream(yml_file);
			if (in != null) {
				obj = yaml.load(in);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(in!=null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	public static Map<String, String> getYamlFile(File yml_file) {
		try {
			Object yamlobj = genYamlObjFormPath(yml_file);
			Map<String, String> resultMap = new HashMap<String, String>(20);
			if (yamlobj != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> ymap = (Map<String, Object>) yamlobj;

				Map<String, Object> flattenedMap = getFlattenedMap(ymap);
				
				for(Entry<String, Object>e:flattenedMap.entrySet()) {
					if(e.getValue() instanceof String) {
						resultMap.put(e.getKey(), (String)e.getValue());
					}
				}
					
				if (!resultMap.isEmpty()) {
					return resultMap;
				} else
					return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static final Map<String, Object> getFlattenedMap(Map<String, Object> source) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		buildFlattenedMap(result, source, null);
		return result;
	}
	
	
	private static void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, String path) {
		for (Entry<String, Object> entry : source.entrySet()) {
			String key = entry.getKey();
			if (StringUtils.isNotBlank(path)) {
				if (key.startsWith("[")) {
					key = path + key;
				} else {
					key = path + '.' + key;
				}
			}
			Object value = entry.getValue();
			if (value instanceof String) {
				result.put(key, value);
			} else if (value instanceof Map) {
				// Need a compound key
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) value;
				buildFlattenedMap(result, map, key);
			} else if (value instanceof Collection) {
				// Need a compound key
				@SuppressWarnings("unchecked")
				Collection<Object> collection = (Collection<Object>) value;
				int count = 0;
				for (Object object : collection) {
					buildFlattenedMap(result, Collections.singletonMap("[" + (count++) + "]", object), key);
				}
			} else {
				result.put(key, (value != null ? value : ""));
			}
		}
	}

}
