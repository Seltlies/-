import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import fangchanjingliList from '../pages/fangchanjingli/list'
import fangchanjingliDetail from '../pages/fangchanjingli/detail'
import fangchanjingliAdd from '../pages/fangchanjingli/add'
import fangwuleixingList from '../pages/fangwuleixing/list'
import fangwuleixingDetail from '../pages/fangwuleixing/detail'
import fangwuleixingAdd from '../pages/fangwuleixing/add'
import fangchanxinxiList from '../pages/fangchanxinxi/list'
import fangchanxinxiDetail from '../pages/fangchanxinxi/detail'
import fangchanxinxiAdd from '../pages/fangchanxinxi/add'
import yuyuekanfangList from '../pages/yuyuekanfang/list'
import yuyuekanfangDetail from '../pages/yuyuekanfang/detail'
import yuyuekanfangAdd from '../pages/yuyuekanfang/add'
import kanfangxinxiList from '../pages/kanfangxinxi/list'
import kanfangxinxiDetail from '../pages/kanfangxinxi/detail'
import kanfangxinxiAdd from '../pages/kanfangxinxi/add'
import goufangdingdanList from '../pages/goufangdingdan/list'
import goufangdingdanDetail from '../pages/goufangdingdan/detail'
import goufangdingdanAdd from '../pages/goufangdingdan/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'fangchanjingli',
					component: fangchanjingliList
				},
				{
					path: 'fangchanjingliDetail',
					component: fangchanjingliDetail
				},
				{
					path: 'fangchanjingliAdd',
					component: fangchanjingliAdd
				},
				{
					path: 'fangwuleixing',
					component: fangwuleixingList
				},
				{
					path: 'fangwuleixingDetail',
					component: fangwuleixingDetail
				},
				{
					path: 'fangwuleixingAdd',
					component: fangwuleixingAdd
				},
				{
					path: 'fangchanxinxi',
					component: fangchanxinxiList
				},
				{
					path: 'fangchanxinxiDetail',
					component: fangchanxinxiDetail
				},
				{
					path: 'fangchanxinxiAdd',
					component: fangchanxinxiAdd
				},
				{
					path: 'yuyuekanfang',
					component: yuyuekanfangList
				},
				{
					path: 'yuyuekanfangDetail',
					component: yuyuekanfangDetail
				},
				{
					path: 'yuyuekanfangAdd',
					component: yuyuekanfangAdd
				},
				{
					path: 'kanfangxinxi',
					component: kanfangxinxiList
				},
				{
					path: 'kanfangxinxiDetail',
					component: kanfangxinxiDetail
				},
				{
					path: 'kanfangxinxiAdd',
					component: kanfangxinxiAdd
				},
				{
					path: 'goufangdingdan',
					component: goufangdingdanList
				},
				{
					path: 'goufangdingdanDetail',
					component: goufangdingdanDetail
				},
				{
					path: 'goufangdingdanAdd',
					component: goufangdingdanAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
