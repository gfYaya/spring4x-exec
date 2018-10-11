package com.smart.anno;

public class ForumService {
    @NeedTest()
	public void deleteForum(int forumId){
		System.out.println("删除论坛模块："+forumId);
	}
    /**
     * 
     * @param topicId
     */
    @NeedTest(value=false)
    public void deleteTopic(int topicId){
		System.out.println("删除论坛主题："+topicId);
	}	
}
