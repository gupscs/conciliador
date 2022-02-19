(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d5fdd004"],{"0f9e":function(e,t,r){"use strict";r.r(t);var a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"auth-wrapper auth-v2"},[r("b-row",{staticClass:"auth-inner m-0"},[r("b-link",{staticClass:"brand-logo"},[r("vuexy-logo"),r("h2",{staticClass:"brand-text text-primary ml-1"},[e._v("Vuexy")])],1),r("b-col",{staticClass:"d-none d-lg-flex align-items-center p-5",attrs:{lg:"8"}},[r("div",{staticClass:"w-100 d-lg-flex align-items-center justify-content-center px-5"},[r("b-img",{attrs:{fluid:"",src:e.imgUrl,alt:"Register V2"}})],1)]),r("b-col",{staticClass:"d-flex align-items-center auth-bg px-2 p-lg-5",attrs:{lg:"4"}},[r("b-col",{staticClass:"px-xl-2 mx-auto",attrs:{sm:"8",md:"6",lg:"12"}},[r("b-card-title",{staticClass:"font-weight-bold mb-1",attrs:{"title-tag":"h2"}},[e._v(" Adventure starts here 🚀 ")]),r("b-card-text",{staticClass:"mb-2"},[e._v(" Make your app management easy and fun! ")]),r("b-alert",{directives:[{name:"height-fade",rawName:"v-height-fade.appear",modifiers:{appear:!0}}],staticClass:"mb-0",attrs:{variant:"danger",dismissible:""},model:{value:e.showDismissibleErrorAlert,callback:function(t){e.showDismissibleErrorAlert=t},expression:"showDismissibleErrorAlert"}},[r("div",{staticClass:"alert-body"},[r("feather-icon",{staticClass:"mr-25",attrs:{icon:"FrownIcon"}}),r("span",{staticClass:"ml-25"},[e._v("Algo deu errado!! Já estamos trabalhando para resolver, tente novamente mais tarde")])],1)]),r("validation-observer",{ref:"registerForm"},[r("b-form",{staticClass:"auth-register-form mt-2"},[r("b-form-group",{attrs:{label:"CNPJ","label-for":"register-identificationNo"}},[r("validation-provider",{attrs:{name:"cnpj",rules:"required|integer|digits:14"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-form-input",{attrs:{id:"register-identificationNo",name:"register-identificationNo",placeholder:"12.345.678/9012-34 (somente números)",state:!(a.length>0||!e.validationExistIdentificationNo)&&null},model:{value:e.register.identificationNo,callback:function(t){e.$set(e.register,"identificationNo",t)},expression:"register.identificationNo"}}),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])}),r("b-form-invalid-feedback",{attrs:{state:e.validationExistIdentificationNo}},[e._v(" CNPJ já cadastrado ")])],1),r("b-form-group",{attrs:{label:"Nome da Empresa","label-for":"register-company-name"}},[r("validation-provider",{attrs:{name:"register-company-name",rules:"required"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-form-input",{attrs:{id:"register-companyName",name:"register-company-name",placeholder:"Nome da sua Empresa",state:!(a.length>0)&&null},model:{value:e.register.name,callback:function(t){e.$set(e.register,"name",t)},expression:"register.name"}}),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])})],1),r("b-form-group",{attrs:{label:"Celular","label-for":"register-phone1"}},[r("validation-provider",{attrs:{name:"register-phone1",rules:"required|min:11|integer"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-form-input",{attrs:{id:"register-phone1",name:"register-phone1",placeholder:"Celular/Whatsapp (somente números)",state:!(a.length>0)&&null},model:{value:e.register.phone1,callback:function(t){e.$set(e.register,"phone1",t)},expression:"register.phone1"}}),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])})],1),r("b-form-group",{attrs:{label:"Nome do Usuário","label-for":"register-name"}},[r("validation-provider",{attrs:{name:"register-name",rules:"required"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-form-input",{attrs:{id:"register-name",name:"register-name",placeholder:"Seu nome",state:!(a.length>0)&&null},model:{value:e.register.userName,callback:function(t){e.$set(e.register,"userName",t)},expression:"register.userName"}}),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])})],1),r("b-form-group",{attrs:{label:"Email","label-for":"register-email"}},[r("validation-provider",{attrs:{name:"Email",rules:"required|email"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-form-input",{attrs:{id:"register-email",name:"register-email",state:!(a.length>0||!e.validationExistUsername)&&null,placeholder:"john@example.com"},model:{value:e.register.email,callback:function(t){e.$set(e.register,"email",t)},expression:"register.email"}}),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])}),r("b-form-invalid-feedback",{attrs:{state:e.validationExistUsername}},[e._v(" Email já cadastrado ")])],1),r("b-form-group",{attrs:{"label-for":"register-password",label:"Password"}},[r("validation-provider",{attrs:{name:"Password",vid:"Password",rules:"required|password"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-input-group",{staticClass:"input-group-merge",class:a.length>0?"is-invalid":null},[r("b-form-input",{staticClass:"form-control-merge",attrs:{id:"register-password",type:e.passwordFieldType,state:!(a.length>0)&&null,name:"register-password",placeholder:"············"},model:{value:e.register.password,callback:function(t){e.$set(e.register,"password",t)},expression:"register.password"}}),r("b-input-group-append",{attrs:{"is-text":""}},[r("feather-icon",{staticClass:"cursor-pointer",attrs:{icon:e.passwordToggleIcon},on:{click:e.togglePasswordVisibility}})],1)],1),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])})],1),r("b-form-group",{attrs:{"label-for":"register-confimed-password",label:"Confirm Password"}},[r("validation-provider",{attrs:{name:"Confirm Password",rules:"required|confirmed:Password"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-input-group",{staticClass:"input-group-merge",class:a.length>0?"is-invalid":null},[r("b-form-input",{staticClass:"form-control-merge",attrs:{id:"register-confirm-password",type:e.passwordFieldType,state:!(a.length>0)&&null,name:"register-confirm-password",placeholder:"············"},model:{value:e.register.confirmedPassword,callback:function(t){e.$set(e.register,"confirmedPassword",t)},expression:"register.confirmedPassword"}}),r("b-input-group-append",{attrs:{"is-text":""}},[r("feather-icon",{staticClass:"cursor-pointer",attrs:{icon:e.passwordToggleIcon},on:{click:e.togglePasswordVisibility}})],1)],1),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])})],1),r("b-form-group",[r("validation-provider",{attrs:{name:"termos",rules:"required"},scopedSlots:e._u([{key:"default",fn:function(t){var a=t.errors;return[r("b-form-checkbox",{attrs:{id:"register-privacy-policy",name:"checkbox-1"},model:{value:e.register.terms,callback:function(t){e.$set(e.register,"terms",t)},expression:"register.terms"}},[e._v(" Aceito os "),r("b-link",{directives:[{name:"ripple",rawName:"v-ripple.400",value:"rgba(113, 102, 240, 0.15)",expression:"'rgba(113, 102, 240, 0.15)'",modifiers:{400:!0}},{name:"b-modal",rawName:"v-b-modal.modal-scrollable",modifiers:{"modal-scrollable":!0}}],attrs:{variant:"outline-primary"}},[e._v(" Termos & Condições ")])],1),r("small",{staticClass:"text-danger"},[e._v(e._s(a[0]))])]}}])})],1),r("b-button",{attrs:{variant:"primary",block:"",type:"submit"},on:{click:function(t){return t.preventDefault(),e.validationForm.apply(null,arguments)}}},[e._v(" Sign up ")])],1)],1),r("p",{staticClass:"text-center mt-2"},[r("span",[e._v("Already have an account?")]),r("b-link",{attrs:{to:{name:"login"}}},[r("span",[e._v(" Sign in instead")])])],1)],1)],1)],1),r("b-modal",{attrs:{id:"modal-scrollable",scrollable:"",title:"Termos & Condições","ok-only":"","ok-title":"OK","cancel-variant":"outline-secondary"}},e._l(e.scrollContent,(function(t,a){return r("b-card-text",{key:a},[e._v(" "+e._s(t)+" ")])})),1)],1)},s=[],i=(r("99af"),r("ac1f"),r("5319"),r("7bb1")),o=r("088b"),n=r("a15b7"),l=r("4918"),d=r("b28b"),c=r("aa59"),u=r("1947"),m=r("11de"),p=r("d6e4"),g=r("4968"),f=r("c3e6"),v=r("8226"),b=r("4797"),h=r("5e12"),x=r("ccc0"),y=r("5fda"),w=r("3010"),C=r("8f03"),k=r("b91d"),_=r("4360"),E=r("223c"),N=r("09bb"),T=r("7100"),I=r("10f6"),R=r("e009"),S={components:{VuexyLogo:o["a"],BRow:n["a"],BImg:l["a"],BCol:d["a"],BLink:c["a"],BButton:u["a"],BForm:m["a"],BCardText:p["a"],BCardTitle:g["a"],BFormCheckbox:f["a"],BFormGroup:v["a"],BFormInput:b["a"],BInputGroup:h["a"],BInputGroupAppend:x["a"],ValidationProvider:i["b"],ValidationObserver:i["a"],BAlert:y["a"],BFormInvalidFeedback:w["a"]},directives:{"height-fade":I["a"],Ripple:R["a"]},mixins:[k["a"]],data:function(){return{register:{userName:"",email:"",password:"",confirmedPassword:"",identificationNo:"",name:"",phone1:"",terms:""},checkRegister:{existUsername:!1,existIdentificationNo:!1},sideImg:r("67df"),required:C["d"],email:C["b"],password:C["c"],confirmed:C["a"],scrollContent:["implementar os termos e condições puxando de um serviço"],showDismissibleErrorAlert:!1}},computed:{passwordToggleIcon:function(){return"password"===this.passwordFieldType?"EyeIcon":"EyeOffIcon"},imgUrl:function(){return"dark"===_["a"].state.appConfig.layout.skin?(this.sideImg=r("99b7"),this.sideImg):this.sideImg},validationExistIdentificationNo:function(){return!this.checkRegister.existIdentificationNo},validationExistUsername:function(){return!this.checkRegister.existUsername}},methods:{validationForm:function(){var e=this;this.showDismissibleErrorAlert=!1,this.$refs.registerForm.validate().then((function(t){t&&N["a"].get("/organizational/organizational/registerCheck/".concat(e.register.identificationNo,"/").concat(e.register.email)).then((function(t){e.checkRegister=t.data,console.log(t.data),e.checkRegister.existUsername||e.checkRegister.existIdentificationNo||(T["a"].setToken(""),N["a"].post("/organizational/organizational/register",e.register).then((function(t){e.$router.replace("/login").then((function(){e.$toast({component:E["a"],props:{title:"Registardo com Sucesso",icon:"EditIcon",variant:"success"}})}))})).catch((function(t){console.log(t),e.showDismissibleErrorAlert=!0})))})).catch((function(t){console.log(t),e.showDismissibleErrorAlert=!0}))}))}}},B=S,P=(r("add3"),r("2877")),$=Object(P["a"])(B,a,s,!1,null,null,null);t["default"]=$.exports},"166e":function(e,t,r){},5319:function(e,t,r){"use strict";var a=r("d784"),s=r("825a"),i=r("7b0b"),o=r("50c4"),n=r("a691"),l=r("1d80"),d=r("8aa5"),c=r("14c3"),u=Math.max,m=Math.min,p=Math.floor,g=/\$([$&'`]|\d\d?|<[^>]*>)/g,f=/\$([$&'`]|\d\d?)/g,v=function(e){return void 0===e?e:String(e)};a("replace",2,(function(e,t,r,a){var b=a.REGEXP_REPLACE_SUBSTITUTES_UNDEFINED_CAPTURE,h=a.REPLACE_KEEPS_$0,x=b?"$":"$0";return[function(r,a){var s=l(this),i=void 0==r?void 0:r[e];return void 0!==i?i.call(r,s,a):t.call(String(s),r,a)},function(e,a){if(!b&&h||"string"===typeof a&&-1===a.indexOf(x)){var i=r(t,e,this,a);if(i.done)return i.value}var l=s(e),p=String(this),g="function"===typeof a;g||(a=String(a));var f=l.global;if(f){var w=l.unicode;l.lastIndex=0}var C=[];while(1){var k=c(l,p);if(null===k)break;if(C.push(k),!f)break;var _=String(k[0]);""===_&&(l.lastIndex=d(p,o(l.lastIndex),w))}for(var E="",N=0,T=0;T<C.length;T++){k=C[T];for(var I=String(k[0]),R=u(m(n(k.index),p.length),0),S=[],B=1;B<k.length;B++)S.push(v(k[B]));var P=k.groups;if(g){var $=[I].concat(S,R,p);void 0!==P&&$.push(P);var A=String(a.apply(void 0,$))}else A=y(I,p,R,S,P,a);R>=N&&(E+=p.slice(N,R)+A,N=R+I.length)}return E+p.slice(N)}];function y(e,r,a,s,o,n){var l=a+e.length,d=s.length,c=f;return void 0!==o&&(o=i(o),c=g),t.call(n,c,(function(t,i){var n;switch(i.charAt(0)){case"$":return"$";case"&":return e;case"`":return r.slice(0,a);case"'":return r.slice(l);case"<":n=o[i.slice(1,-1)];break;default:var c=+i;if(0===c)return t;if(c>d){var u=p(c/10);return 0===u?t:u<=d?void 0===s[u-1]?i.charAt(1):s[u-1]+i.charAt(1):t}n=s[c-1]}return void 0===n?"":n}))}}))},"67df":function(e,t,r){e.exports=r.p+"img/register-v2.0a9e487c.svg"},"99b7":function(e,t,r){e.exports=r.p+"img/register-v2-dark.271932dd.svg"},add3:function(e,t,r){"use strict";r("166e")},b91d:function(e,t,r){"use strict";r.d(t,"a",(function(){return a}));var a={data:function(){return{passwordFieldType:"password"}},methods:{togglePasswordVisibility:function(){this.passwordFieldType="password"===this.passwordFieldType?"text":"password"}}}},d6e4:function(e,t,r){"use strict";r.d(t,"a",(function(){return d}));var a=r("2b0e"),s=r("b42e"),i=r("c637"),o=r("a723"),n=r("cf75"),l=Object(n["d"])({textTag:Object(n["c"])(o["r"],"p")},i["o"]),d=a["default"].extend({name:i["o"],functional:!0,props:l,render:function(e,t){var r=t.props,a=t.data,i=t.children;return e(r.textTag,Object(s["a"])(a,{staticClass:"card-text"}),i)}})},e009:function(e,t,r){"use strict";var a={bind:function(e,t){var r={event:"mousedown",transition:600};s(Object.keys(t.modifiers),r),e.addEventListener(r.event,(function(r){n(r,e,t.value)}));var i=t.value||a.color||"rgba(0, 0, 0, 0.35)",o=a.zIndex||"9999";function n(e,t){var a=t,s=parseInt(getComputedStyle(a).borderWidth.replace("px","")),n=a.getBoundingClientRect(),l=n.left,d=n.top,c=a.offsetWidth,u=a.offsetHeight,m=e.clientX-l,p=e.clientY-d,g=Math.max(m,c-m),f=Math.max(p,u-p),v=window.getComputedStyle(a),b=Math.sqrt(g*g+f*f),h=s>0?s:0,x=document.createElement("div"),y=document.createElement("div");y.className="ripple-container",x.className="ripple",x.style.marginTop="0px",x.style.marginLeft="0px",x.style.width="1px",x.style.height="1px",x.style.transition="all "+r.transition+"ms cubic-bezier(0.4, 0, 0.2, 1)",x.style.borderRadius="50%",x.style.pointerEvents="none",x.style.position="relative",x.style.zIndex=o,x.style.backgroundColor=i,y.style.position="absolute",y.style.left=0-h+"px",y.style.top=0-h+"px",y.style.height="0",y.style.width="0",y.style.pointerEvents="none",y.style.overflow="hidden";var w=a.style.position.length>0?a.style.position:getComputedStyle(a).position;function C(){setTimeout((function(){x.style.backgroundColor="rgba(0, 0, 0, 0)"}),250),setTimeout((function(){y.parentNode.removeChild(y)}),850),t.removeEventListener("mouseup",C,!1),setTimeout((function(){for(var e=!0,t=0;t<a.childNodes.length;t++)"ripple-container"===a.childNodes[t].className&&(e=!1);e&&(a.style.position="static"!==w?w:"")}),r.transition+250)}"relative"!==w&&(a.style.position="relative"),y.appendChild(x),a.appendChild(y),x.style.marginLeft=m+"px",x.style.marginTop=p+"px",y.style.width=c+"px",y.style.height=u+"px",y.style.borderTopLeftRadius=v.borderTopLeftRadius,y.style.borderTopRightRadius=v.borderTopRightRadius,y.style.borderBottomLeftRadius=v.borderBottomLeftRadius,y.style.borderBottomRightRadius=v.borderBottomRightRadius,y.style.direction="ltr",setTimeout((function(){x.style.width=2*b+"px",x.style.height=2*b+"px",x.style.marginLeft=m-b+"px",x.style.marginTop=p-b+"px"}),0),"mousedown"===e.type?t.addEventListener("mouseup",C,!1):C()}}};function s(e,t){e.forEach((function(e){isNaN(Number(e))?t.event=e:t.transition=e}))}t["a"]=a}}]);
//# sourceMappingURL=chunk-d5fdd004.00720047.js.map