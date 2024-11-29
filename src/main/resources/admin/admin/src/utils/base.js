const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootel9ifp4c/",
            name: "springbootel9ifp4c",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootel9ifp4c/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于JavaWeb的房产信息管理系统的设计与实现"
        } 
    }
}
export default base
