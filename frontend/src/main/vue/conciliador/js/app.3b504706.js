(function(e){function t(t){for(var a,o,u=t[0],i=t[1],s=t[2],d=0,l=[];d<u.length;d++)o=u[d],Object.prototype.hasOwnProperty.call(c,o)&&c[o]&&l.push(c[o][0]),c[o]=0;for(a in i)Object.prototype.hasOwnProperty.call(i,a)&&(e[a]=i[a]);f&&f(t);while(l.length)l.shift()();return r.push.apply(r,s||[]),n()}function n(){for(var e,t=0;t<r.length;t++){for(var n=r[t],a=!0,o=1;o<n.length;o++){var u=n[o];0!==c[u]&&(a=!1)}a&&(r.splice(t--,1),e=i(i.s=n[0]))}return e}var a={},o={app:0},c={app:0},r=[];function u(e){return i.p+"js/"+({}[e]||e)+"."+{"chunk-031be6ee":"22f10f8d","chunk-0332c14f":"d6e578c8","chunk-2d22bcc3":"38c292bc","chunk-dc5f5c4c":"6811b8c3","chunk-e2b98b6c":"a69be777","chunk-34b8755a":"ed1e1eed","chunk-16e6174b":"98fbe49d","chunk-438309fa":"31acd5cc","chunk-2d0d6b32":"44df4607","chunk-4ee1d8a4":"ab5d9a0e","chunk-90215256":"a6b31d87","chunk-d7a15b1e":"00bfdc2a","chunk-1fecd4be":"f3ee3394","chunk-7049ec69":"f26ac0cf","chunk-912f0a9e":"687534e8","chunk-631dda4c":"8eb3f11e","chunk-7c36477d":"4835bc93","chunk-d5fdd004":"00720047","chunk-fd539da0":"7d80a21d"}[e]+".js"}function i(t){if(a[t])return a[t].exports;var n=a[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,i),n.l=!0,n.exports}i.e=function(e){var t=[],n={"chunk-dc5f5c4c":1,"chunk-34b8755a":1,"chunk-16e6174b":1,"chunk-438309fa":1,"chunk-4ee1d8a4":1,"chunk-90215256":1,"chunk-1fecd4be":1,"chunk-7049ec69":1,"chunk-912f0a9e":1,"chunk-7c36477d":1,"chunk-d5fdd004":1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=new Promise((function(t,n){for(var a="css/"+({}[e]||e)+"."+{"chunk-031be6ee":"31d6cfe0","chunk-0332c14f":"31d6cfe0","chunk-2d22bcc3":"31d6cfe0","chunk-dc5f5c4c":"fd79da1b","chunk-e2b98b6c":"31d6cfe0","chunk-34b8755a":"4dbea570","chunk-16e6174b":"0c8854d0","chunk-438309fa":"4dbea570","chunk-2d0d6b32":"31d6cfe0","chunk-4ee1d8a4":"fd79da1b","chunk-90215256":"4dbea570","chunk-d7a15b1e":"31d6cfe0","chunk-1fecd4be":"04136afe","chunk-7049ec69":"eb2c40eb","chunk-912f0a9e":"42cb0a4a","chunk-631dda4c":"31d6cfe0","chunk-7c36477d":"7588fbd8","chunk-d5fdd004":"7588fbd8","chunk-fd539da0":"31d6cfe0"}[e]+".css",c=i.p+a,r=document.getElementsByTagName("link"),u=0;u<r.length;u++){var s=r[u],d=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(d===a||d===c))return t()}var l=document.getElementsByTagName("style");for(u=0;u<l.length;u++){s=l[u],d=s.getAttribute("data-href");if(d===a||d===c)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var a=t&&t.target&&t.target.src||c,r=new Error("Loading CSS chunk "+e+" failed.\n("+a+")");r.code="CSS_CHUNK_LOAD_FAILED",r.request=a,delete o[e],f.parentNode.removeChild(f),n(r)},f.href=c;var h=document.getElementsByTagName("head")[0];h.appendChild(f)})).then((function(){o[e]=0})));var a=c[e];if(0!==a)if(a)t.push(a[2]);else{var r=new Promise((function(t,n){a=c[e]=[t,n]}));t.push(a[2]=r);var s,d=document.createElement("script");d.charset="utf-8",d.timeout=120,i.nc&&d.setAttribute("nonce",i.nc),d.src=u(e);var l=new Error;s=function(t){d.onerror=d.onload=null,clearTimeout(f);var n=c[e];if(0!==n){if(n){var a=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;l.message="Loading chunk "+e+" failed.\n("+a+": "+o+")",l.name="ChunkLoadError",l.type=a,l.request=o,n[1](l)}c[e]=void 0}};var f=setTimeout((function(){s({type:"timeout",target:d})}),12e4);d.onerror=d.onload=s,document.head.appendChild(d)}return Promise.all(t)},i.m=e,i.c=a,i.d=function(e,t,n){i.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(i.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var a in e)i.d(n,a,function(t){return e[t]}.bind(null,a));return n},i.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(t,"a",t),t},i.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},i.p="/",i.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],d=s.push.bind(s);s.push=t,s=s.slice();for(var l=0;l<s.length;l++)t(s[l]);var f=d;r.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"1dff":function(e,t,n){"use strict";n.d(t,"b",(function(){return a})),n.d(t,"a",(function(){return o})),n.d(t,"c",(function(){return c}));var a={},o={},c={app:{appName:"Vuexy",appLogoImage:n("ed29")},layout:{isRTL:!1,skin:"light",routerTransition:"zoom-fade",type:"vertical",contentWidth:"full",menu:{hidden:!1,isCollapsed:!1},navbar:{type:"sticky",backgroundColor:""},footer:{type:"static"},customizer:!0,enableScrollToTop:!0}}},4131:function(e,t,n){},4360:function(e,t,n){"use strict";var a=n("2b0e"),o=n("2f62"),c=n("1dff"),r={namespaced:!0,state:{windowWidth:0,shallShowOverlay:!1},getters:{currentBreakPoint:function(e){var t=e.windowWidth;return t>=c["a"].xl?"xl":t>=c["a"].lg?"lg":t>=c["a"].md?"md":t>=c["a"].sm?"sm":"xs"}},mutations:{UPDATE_WINDOW_WIDTH:function(e,t){e.windowWidth=t},TOGGLE_OVERLAY:function(e,t){e.shallShowOverlay=void 0!==t?t:!e.shallShowOverlay}},actions:{}},u=(n("ac1f"),n("466d"),{namespaced:!0,state:{layout:{isRTL:c["c"].layout.isRTL,skin:localStorage.getItem("vuexy-skin")||c["c"].layout.skin,routerTransition:c["c"].layout.routerTransition,type:c["c"].layout.type,contentWidth:c["c"].layout.contentWidth,menu:{hidden:c["c"].layout.menu.hidden},navbar:{type:c["c"].layout.navbar.type,backgroundColor:c["c"].layout.navbar.backgroundColor},footer:{type:c["c"].layout.footer.type}}},getters:{},mutations:{TOGGLE_RTL:function(e){e.layout.isRTL=!e.layout.isRTL,document.documentElement.setAttribute("dir",e.layout.isRTL?"rtl":"ltr")},UPDATE_SKIN:function(e,t){e.layout.skin=t,localStorage.setItem("vuexy-skin",t),"dark"===t?document.body.classList.add("dark-layout"):document.body.className.match("dark-layout")&&document.body.classList.remove("dark-layout")},UPDATE_ROUTER_TRANSITION:function(e,t){e.layout.routerTransition=t},UPDATE_LAYOUT_TYPE:function(e,t){e.layout.type=t},UPDATE_CONTENT_WIDTH:function(e,t){e.layout.contentWidth=t},UPDATE_NAV_MENU_HIDDEN:function(e,t){e.layout.menu.hidden=t},UPDATE_NAVBAR_CONFIG:function(e,t){Object.assign(e.layout.navbar,t)},UPDATE_FOOTER_CONFIG:function(e,t){Object.assign(e.layout.footer,t)}},actions:{}}),i={namespaced:!0,state:{isVerticalMenuCollapsed:c["c"].layout.menu.isCollapsed},getters:{},mutations:{UPDATE_VERTICAL_MENU_COLLAPSED:function(e,t){e.isVerticalMenuCollapsed=t}},actions:{}};a["default"].use(o["a"]);t["a"]=new o["a"].Store({modules:{app:r,appConfig:u,verticalMenu:i},strict:Object({NODE_ENV:"production",BASE_URL:"/"}).DEV})},"56d7":function(e,t,n){"use strict";n.r(t);n("e623"),n("e379"),n("5dc8"),n("37e1");var a,o,c=n("2b0e"),r=n("51c2"),u=n("dbbe"),i=n("ed09"),s=n("a18c"),d=n("4360"),l=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"h-100",class:[e.skinClasses],attrs:{id:"app"}},[n(e.layout,{tag:"component"},[n("router-view")],1)],1)},f=[],h=(n("d3b7"),n("3ca3"),n("ddb0"),n("498a"),n("a9e3"),n("fb6a"),n("1dff")),p=n("04b0"),b=n("b8f2"),m=n("16ce"),g=function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-d7a15b1e"),n.e("chunk-1fecd4be"),n.e("chunk-912f0a9e")]).then(n.bind(null,"03d1"))},k=function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-d7a15b1e"),n.e("chunk-1fecd4be"),n.e("chunk-7049ec69")]).then(n.bind(null,"2607"))},y=function(){return n.e("chunk-2d22bcc3").then(n.bind(null,"f102"))},v={components:{LayoutHorizontal:k,LayoutVertical:g,LayoutFull:y},computed:{layout:function(){return"full"===this.$route.meta.layout?"layout-full":"layout-".concat(this.contentLayoutType)},contentLayoutType:function(){return this.$store.state.appConfig.layout.type}},beforeCreate:function(){for(var e=["primary","secondary","success","info","warning","danger","light","dark"],t=0,n=e.length;t<n;t++)h["b"][e[t]]=Object(m["a"])("--".concat(e[t]),document.documentElement).value.trim();for(var a=["xs","sm","md","lg","xl"],o=0,c=a.length;o<c;o++)h["a"][a[o]]=Number(Object(m["a"])("--breakpoint-".concat(a[o]),document.documentElement).value.slice(0,-2));var r=h["c"].layout.isRTL;document.documentElement.setAttribute("dir",r?"rtl":"ltr")},setup:function(){var e=Object(b["a"])(),t=e.skin,n=e.skinClasses;"dark"===t.value&&document.body.classList.add("dark-layout"),Object(p["provideToast"])({hideProgressBar:!0,closeOnClick:!1,closeButton:!1,icon:!1,timeout:3e3,transition:"Vue-Toastification__fade"}),d["a"].commit("app/UPDATE_WINDOW_WIDTH",window.innerWidth);var a=Object(m["b"])(),o=a.width;return Object(i["watch"])(o,(function(e){d["a"].commit("app/UPDATE_WINDOW_WIDTH",e)})),{skinClasses:n}}},T=v,O=n("2877"),C=Object(O["a"])(T,l,f,!1,null,null,null),j=C.exports,E=(n("b0c0"),n("5530")),A=n("0a35"),_={name:"FeatherIcon",functional:!0,props:{icon:{required:!0,type:[String,Object]},size:{type:String,default:"14"},badge:{type:[String,Object,Number],default:null},badgeClasses:{type:[String,Object,Array],default:"badge-primary"}},render:function(e,t){var n=t.props,a=t.data,o=e(A[n.icon],Object(E["a"])({props:{size:n.size}},a));if(!n.badge)return o;var c=e("span",{staticClass:"badge badge-up badge-pill",class:n.badgeClasses},[n.badge]);return e("span",{staticClass:"feather-icon position-relative"},[o,c])}},w=_,P=(n("8bd0"),Object(O["a"])(w,a,o,!1,null,null,null)),N=P.exports;c["default"].component(N.name,N);var I=n("2b88"),D=n.n(I);c["default"].use(D.a);var x=n("6c42");n("cc0f");c["default"].use(x["default"],{hideProgressBar:!0,closeOnClick:!1,closeButton:!1,icon:!1,timeout:3e3,transition:"Vue-Toastification__fade"});n("7f80");var L=n("4eb5"),S=n.n(L);c["default"].use(S.a);var R=n("5886");c["default"].use(R["default"]);var U=n("4a7a"),M=n.n(U);M.a.props.components.default=function(){return{Deselect:{render:function(e){return e("feather-icon",{props:{size:"14",icon:"XIcon"}})}},OpenIndicator:{render:function(e){return e("feather-icon",{props:{size:"15",icon:"ChevronDownIcon"},class:"open-indicator"})}}}},c["default"].component(M.a);var W=n("2536"),F=n.n(W);c["default"].use(F.a),c["default"].use(r["a"]),c["default"].use(u["a"]),n("59b8"),c["default"].use(i["default"]),n("4131"),n("78a7"),c["default"].config.productionTip=!1,new c["default"]({router:s["a"],store:d["a"],render:function(e){return e(j)}}).$mount("#app")},"59b8":function(e,t,n){},"602d4":function(e,t,n){},7100:function(e,t,n){"use strict";var a=n("5530"),o=n("d4ec"),c=n("bee2"),r=n("ade3"),u=(n("99af"),n("d3b7"),n("4de4"),{loginEndpoint:"/oauth/oauth/token",registerEndpoint:"/jwt/register",refreshEndpoint:"/jwt/refresh-token",logoutEndpoint:"/jwt/logout",tokenType:"Bearer",storageTokenKeyName:"accessToken",storageRefreshTokenKeyName:"refreshToken"}),i=function(){function e(t,n){var c=this;Object(o["a"])(this,e),Object(r["a"])(this,"axiosIns",null),Object(r["a"])(this,"jwtConfig",Object(a["a"])({},u)),Object(r["a"])(this,"isAlreadyFetchingAccessToken",!1),Object(r["a"])(this,"subscribers",[]),this.axiosIns=t,this.jwtConfig=Object(a["a"])(Object(a["a"])({},this.jwtConfig),n),this.axiosIns.interceptors.request.use((function(e){var t=c.getToken();return t&&(e.headers.Authorization="".concat(c.jwtConfig.tokenType," ").concat(t)),e}),(function(e){return Promise.reject(e)})),this.axiosIns.interceptors.response.use((function(e){return e}),(function(e){var t=e.config,n=e.response,a=t;if(n&&401===n.status){c.isAlreadyFetchingAccessToken||(c.isAlreadyFetchingAccessToken=!0,c.refreshToken().then((function(e){c.isAlreadyFetchingAccessToken=!1,c.setToken(e.data.accessToken),c.setRefreshToken(e.data.refreshToken),c.onAccessTokenFetched(e.data.accessToken)})));var o=new Promise((function(e){c.addSubscriber((function(t){a.headers.Authorization="".concat(c.jwtConfig.tokenType," ").concat(t),e(c.axiosIns(a))}))}));return o}return Promise.reject(e)}))}return Object(c["a"])(e,[{key:"onAccessTokenFetched",value:function(e){this.subscribers=this.subscribers.filter((function(t){return t(e)}))}},{key:"addSubscriber",value:function(e){this.subscribers.push(e)}},{key:"getToken",value:function(){return localStorage.getItem(this.jwtConfig.storageTokenKeyName)}},{key:"getRefreshToken",value:function(){return localStorage.getItem(this.jwtConfig.storageRefreshTokenKeyName)}},{key:"setToken",value:function(e){localStorage.setItem(this.jwtConfig.storageTokenKeyName,e)}},{key:"setRefreshToken",value:function(e){localStorage.setItem(this.jwtConfig.storageRefreshTokenKeyName,e)}},{key:"login",value:function(){for(var e,t=arguments.length,n=new Array(t),a=0;a<t;a++)n[a]=arguments[a];return(e=this.axiosIns).post.apply(e,[this.jwtConfig.loginEndpoint].concat(n))}},{key:"register",value:function(){for(var e,t=arguments.length,n=new Array(t),a=0;a<t;a++)n[a]=arguments[a];return(e=this.axiosIns).post.apply(e,[this.jwtConfig.registerEndpoint].concat(n))}},{key:"refreshToken",value:function(){return this.axiosIns.post(this.jwtConfig.refreshEndpoint,{refreshToken:this.getRefreshToken()})}}]),e}();function s(e,t){var n=new i(e,t);return{jwt:n}}var d=n("7f80"),l=s(d["a"],{}),f=l.jwt;t["a"]=f},"78a7":function(e,t,n){},"7f80":function(e,t,n){"use strict";var a=n("2b0e"),o=n("bc3a"),c=n.n(o),r=c.a.create({baseURL:"http://localhost:8090",timeout:1e5});a["default"].prototype.$http=r,t["a"]=r},"8b71":function(e,t,n){"use strict";n.d(t,"a",(function(){return a}));n("7100");var a=function(e){return"CUSTOMER_ADMIN"===e?"/":"client"===e?{name:"access-control"}:{name:"auth-login"}}},"8bd0":function(e,t,n){"use strict";n("602d4")},a18c:function(e,t,n){"use strict";var a=n("2909"),o=(n("99af"),n("2b0e")),c=n("8c4f"),r=(n("d3b7"),n("4b58")),u=n("ff93"),i=JSON.parse(localStorage.getItem("userData")),s=i?i.ability:null,d=(new r["a"](s||u["a"]),n("8b71"),n("3ca3"),n("ddb0"),[{path:"/login",name:"login",component:function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-d7a15b1e"),n.e("chunk-34b8755a"),n.e("chunk-631dda4c"),n.e("chunk-7c36477d")]).then(n.bind(null,"ede4"))},meta:{layout:"full"}},{path:"/register",name:"register",component:function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-d7a15b1e"),n.e("chunk-34b8755a"),n.e("chunk-631dda4c"),n.e("chunk-d5fdd004")]).then(n.bind(null,"0f9e"))},meta:{layout:"full"}},{path:"/error-404",name:"error-404",component:function(){return n.e("chunk-dc5f5c4c").then(n.bind(null,"d80f"))},meta:{layout:"full"}},{path:"/mercado-livre/authorizationRedirect",name:"mercado-livre-authorization-redirect",component:function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-90215256")]).then(n.bind(null,"51bf"))},meta:{layout:"full"}},{path:"*",redirect:"error-404"}]),l=[{path:"/",name:"home",component:function(){return n.e("chunk-0332c14f").then(n.bind(null,"bb51"))},meta:{pageTitle:"Home",breadcrumb:[{text:"Home",active:!0}]}},{path:"/second-page",name:"second-page",component:function(){return n.e("chunk-031be6ee").then(n.bind(null,"19d7"))},meta:{pageTitle:"Second Page",breadcrumb:[{text:"Second Page",active:!0}]}},{path:"/test-page",name:"test-page",component:function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-438309fa"),n.e("chunk-2d0d6b32")]).then(n.bind(null,"7454"))},meta:{pageTitle:"Pagina de Teste",breadcrumb:[{text:"Pagina de Teste",active:!0}]}}],f=[{path:"/my-account/myInfo",name:"my-info",component:function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-d7a15b1e"),n.e("chunk-34b8755a"),n.e("chunk-631dda4c"),n.e("chunk-fd539da0")]).then(n.bind(null,"1ca2"))},meta:{pageTitle:"Meus Dados",breadcrumb:[{text:"Minha Conta"},{text:"Meus Dados",active:!0}]}},{path:"/my-account/myMarketplaces/:tabActived",name:"my-marketplaces",component:function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-438309fa"),n.e("chunk-4ee1d8a4")]).then(n.bind(null,"afaa"))},meta:{pageTitle:"Meus Marketplaces",breadcrumb:[{text:"Minha Conta"},{text:"Meus Marketplaces",active:!0}]}},{path:"/fixed-cost/fixedCost",name:"fixed-cost",component:function(){return Promise.all([n.e("chunk-e2b98b6c"),n.e("chunk-34b8755a"),n.e("chunk-16e6174b")]).then(n.bind(null,"e4c4"))},meta:{pageTitle:"Custos Fixos",breadcrumb:[{text:"Minha Conta"},{text:"Custos Fixos",active:!0}]}}],h=[],p=[];o["default"].use(c["a"]);var b=new c["a"]({mode:"history",base:"/",scrollBehavior:function(){return{x:0,y:0}},routes:[{path:"/",redirect:{name:"home"}}].concat(Object(a["a"])(l),Object(a["a"])(d),Object(a["a"])(f),Object(a["a"])(h),Object(a["a"])(p),[{path:"*",redirect:"error-404"}])});b.afterEach((function(){var e=document.getElementById("loading-bg");e&&(e.style.display="none")}));t["a"]=b},b8f2:function(e,t,n){"use strict";n.d(t,"a",(function(){return c}));var a=n("ed09"),o=n("4360");function c(){var e=Object(a["computed"])({get:function(){return o["a"].state.verticalMenu.isVerticalMenuCollapsed},set:function(e){o["a"].commit("verticalMenu/UPDATE_VERTICAL_MENU_COLLAPSED",e)}}),t=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.isRTL},set:function(e){o["a"].commit("appConfig/TOGGLE_RTL",e)}}),n=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.skin},set:function(e){o["a"].commit("appConfig/UPDATE_SKIN",e)}}),c=Object(a["computed"])((function(){return"bordered"===n.value?"bordered-layout":"semi-dark"===n.value?"semi-dark-layout":null})),r=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.routerTransition},set:function(e){o["a"].commit("appConfig/UPDATE_ROUTER_TRANSITION",e)}}),u=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.type},set:function(e){o["a"].commit("appConfig/UPDATE_LAYOUT_TYPE",e)}});Object(a["watch"])(u,(function(e){"horizontal"===e&&"semi-dark"===n.value&&(n.value="light")}));var i=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.contentWidth},set:function(e){o["a"].commit("appConfig/UPDATE_CONTENT_WIDTH",e)}}),s=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.menu.hidden},set:function(e){o["a"].commit("appConfig/UPDATE_NAV_MENU_HIDDEN",e)}}),d=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.navbar.backgroundColor},set:function(e){o["a"].commit("appConfig/UPDATE_NAVBAR_CONFIG",{backgroundColor:e})}}),l=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.navbar.type},set:function(e){o["a"].commit("appConfig/UPDATE_NAVBAR_CONFIG",{type:e})}}),f=Object(a["computed"])({get:function(){return o["a"].state.appConfig.layout.footer.type},set:function(e){o["a"].commit("appConfig/UPDATE_FOOTER_CONFIG",{type:e})}});return{isVerticalMenuCollapsed:e,isRTL:t,skin:n,skinClasses:c,routerTransition:r,navbarBackgroundColor:d,navbarType:l,footerType:f,layoutType:u,contentWidth:i,isNavMenuHidden:s}}},cc0f:function(e,t,n){},ed29:function(e,t,n){e.exports=n.p+"img/logo.36f34a9f.svg"},ff93:function(e,t,n){"use strict";n.d(t,"a",(function(){return a}));var a=[{action:"read",subject:"Auth"}]}});
//# sourceMappingURL=app.3b504706.js.map