(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0d6b32"],{7454:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("b-card",{attrs:{title:"Contas Adicionadas do Mercado Livre"}},[a("b-alert",{staticClass:"mb-0",attrs:{variant:"danger",dismissible:""},model:{value:e.showDismissibleErrorAlert,callback:function(t){e.showDismissibleErrorAlert=t},expression:"showDismissibleErrorAlert"}},[a("div",{staticClass:"alert-body"},[a("feather-icon",{staticClass:"mr-25",attrs:{icon:"FrownIcon"}}),a("span",{staticClass:"ml-25"},[e._v("Algo deu errado!! Já estamos trabalhando para resolver, tente novamente mais tarde")])],1)]),a("div",{staticClass:"custom-search d-flex justify-content-end"},[a("b-button",{directives:[{name:"ripple",rawName:"v-ripple.400",value:"rgba(113, 102, 240, 0.15)",expression:"'rgba(113, 102, 240, 0.15)'",modifiers:{400:!0}}],attrs:{variant:"outline-primary"},on:{click:e.confirmText}},[e._v(" Adicionar Nova Conta ")])],1),a("br"),a("b-table",{attrs:{responsive:"sm",fields:e.fields,items:e.items,striped:"",small:"",bordered:""}})],1)},o=[],s=a("a15b7"),i=a("b28b"),n=a("1947"),l=a("5fda"),c=a("3010"),d=a("205f"),m=(a("223c"),a("09bb")),b=a("29a1"),u=a("e009"),f=a("c1df"),p=a.n(f),v={components:{BRow:s["a"],BCol:i["a"],BButton:n["a"],BAlert:l["a"],BFormInvalidFeedback:c["a"],BCard:d["a"],BTable:b["a"],Moment:p.a},directives:{Ripple:u["a"]},data:function(){return{fields:[{label:"Usuário",key:"mktPlaceUsername",sortable:!0},{label:"Id",key:"mktPlaceUserId",sortable:!0},{label:"Inserido Por",key:"insertId",sortable:!0},{label:"Inserido Em",key:"insertDate",formatter:"formatDate",sortable:!0},{label:"Atualizado Por",key:"updateId",sortable:!0},{label:"Atualizado Em",key:"updateDate",formatter:"formatDate",sortable:!0},{label:"Ativo/Desativado",key:"enable",sortable:!0}],items:[],showDismissibleErrorAlert:!1}},created:function(){var e=this,t=JSON.parse(localStorage.getItem("userData"));m["a"].get("/mktplace-integrator/mktplace-integrator/getMktPlaceIntegrationConfigUsers/".concat(t.companyId,"/MERCADO_LIVRE")).then((function(t){e.items=t.data})).catch((function(t){e.showDismissibleErrorAlert=!0,console.log(t)}))},methods:{formatDate:function(e){return p()(String(e)).format("DD/MM/YYYY hh:mm:ss")},confirmText:function(){this.$swal({title:"Confirme para continuar",text:"Você será redirecionado para o site do Mercado Livre para autenticação da conta.",icon:"warning",showCancelButton:!0,confirmButtonText:"Sim, pode me redirecionar",customClass:{confirmButton:"btn btn-primary",cancelButton:"btn btn-outline-danger ml-1"},buttonsStyling:!1}).then((function(e){e.value&&(window.location.href="https://google.com/contact")}))}}},h=v,g=a("2877"),w=Object(g["a"])(h,r,o,!1,null,null,null);t["default"]=w.exports}}]);
//# sourceMappingURL=chunk-2d0d6b32.44df4607.js.map