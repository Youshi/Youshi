package com.example.hmqqg.hm.util;

/**
 * FragmentManager存储Fragment用的tag的枚举
 * tag表示Fragment的完整类名
 */
public enum FragmentTag {
	TAG_MANAGE("com.example.hmqqg.hm.fragment.New_MessageFragment"),
	TAG_HOME("com.example.hmqqg.hm.fragment.HomeFragment"),
	TAG_SIGN("com.example.hmqqg.hm.fragment.SignFragment"),
	TAG_MESSAGE("com.example.hmqqg.hm.fragment.MessageFragment"),
	TAG_APPLY("com.example.hmqqg.hm.fragment.ApplyFragment");
	
	String tag;
	
	private FragmentTag(String tag){
		this.tag = tag;
	}
	
	public String getTag(){
		return tag;
	}
}
