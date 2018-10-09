package com.smart.introduce;

//性能监视的接口,即是否一需要开启性能监视,毕竟考虑到有些性能监视消耗性能,来增加可选功能
public interface Monitorable {
   void setMonitorActive(boolean active);
}
