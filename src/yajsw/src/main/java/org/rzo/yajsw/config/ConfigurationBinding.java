package org.rzo.yajsw.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;

public class ConfigurationBinding
{
	Configuration	_conf;
	Map				_utils;
	Map<String, String> _usedEnvVars = new HashMap();

	public ConfigurationBinding(Configuration conf, Map utils)
	{
		_conf = conf;
		_utils = utils;
	}

	public Object getVariable(String name)
	{
		Object result = null;
		if (_utils != null)
			result = _utils.get(name);
		if (result == null)
			result = _conf.getProperty(name);
		if (result != null)
		{
			String r = System.getProperty(name);
			if (result.equals(r))
				_usedEnvVars.put(name, result.toString());
			else 
			{
				r = System.getenv(name);
				if (result.equals(r))
					_usedEnvVars.put(name, result.toString());
			}
				
		}
		return result;
	}

	public Object getProperty(String name)
	{
		return getVariable(name);
	}
	
	public Map<String, String> getUsedEnvVars()
	{
		return _usedEnvVars;
	}

}
