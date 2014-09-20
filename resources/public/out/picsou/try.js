// Compiled by ClojureScript 0.0-2311
goog.provide('picsou.try$');
goog.require('cljs.core');
goog.require('cljs.core.async');
goog.require('goog.string');
goog.require('om.dom');
goog.require('goog.events.EventType');
goog.require('goog.string');
goog.require('om.dom');
goog.require('cljs.core.async');
goog.require('goog.string.format');
goog.require('goog.events');
goog.require('goog.string.format');
goog.require('goog.net.XhrIo');
goog.require('om.core');
goog.require('om.core');
goog.require('cljs.reader');
goog.require('goog.events');
goog.require('cljs.reader');
cljs.core.enable_console_print_BANG_.call(null);
cljs.core.print.call(null,goog.string.format("Booting"),window.location.hash);
picsou.try$.app_state = cljs.core.atom.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"phrase","phrase",1563468627),"tomorrow at 6am",new cljs.core.Keyword(null,"parse","parse",-1162164619),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"rule","rule",729973257),"rule",new cljs.core.Keyword(null,"route","route",329891309),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"rule","rule",729973257),"regex",new cljs.core.Keyword(null,"text","text",-1790561697),"tomorrow at 6am"], null)], null)], null)], null));
picsou.try$.edn_xhr = (function edn_xhr(p__12416){var map__12418 = p__12416;var map__12418__$1 = ((cljs.core.seq_QMARK_.call(null,map__12418))?cljs.core.apply.call(null,cljs.core.hash_map,map__12418):map__12418);var on_complete = cljs.core.get.call(null,map__12418__$1,new cljs.core.Keyword(null,"on-complete","on-complete",-1531183971));var data = cljs.core.get.call(null,map__12418__$1,new cljs.core.Keyword(null,"data","data",-232669377));var url = cljs.core.get.call(null,map__12418__$1,new cljs.core.Keyword(null,"url","url",276297046));var method = cljs.core.get.call(null,map__12418__$1,new cljs.core.Keyword(null,"method","method",55703592));var xhr = (new goog.net.XhrIo());goog.events.listen(xhr,goog.net.EventType.COMPLETE,((function (xhr,map__12418,map__12418__$1,on_complete,data,url,method){
return (function (e){return on_complete.call(null,cljs.reader.read_string.call(null,xhr.getResponseText()));
});})(xhr,map__12418,map__12418__$1,on_complete,data,url,method))
);
return xhr.send(url,method,(cljs.core.truth_(data)?cljs.core.pr_str.call(null,data):null),{"Content-Type": "application/edn"});
});
picsou.try$.get_parse = (function get_parse(app,owner){var phrase = om.core.get_node.call(null,owner,"phrase").value;console.log("Working on: ",phrase);
return picsou.try$.edn_xhr.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"method","method",55703592),"GET",new cljs.core.Keyword(null,"url","url",276297046),("parse/"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(phrase)),new cljs.core.Keyword(null,"on-complete","on-complete",-1531183971),((function (phrase){
return (function (p1__12419_SHARP_){om.core.transact_BANG_.call(null,app,new cljs.core.Keyword(null,"email","email",1415816706),cljs.core.constantly.call(null,new cljs.core.Keyword(null,"email","email",1415816706).cljs$core$IFn$_invoke$arity$1(p1__12419_SHARP_)));
om.core.transact_BANG_.call(null,app,new cljs.core.Keyword(null,"meta","meta",1499536964),cljs.core.constantly.call(null,new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(p1__12419_SHARP_)));
return history.pushState(null,null,("#"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"msg-id","msg-id",-1965771869).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"email","email",1415816706).cljs$core$IFn$_invoke$arity$1(p1__12419_SHARP_)))));
});})(phrase))
], null));
});
picsou.try$.handle_change = (function handle_change(e,owner,p__12420){var map__12422 = p__12420;var map__12422__$1 = ((cljs.core.seq_QMARK_.call(null,map__12422))?cljs.core.apply.call(null,cljs.core.hash_map,map__12422):map__12422);var state = map__12422__$1;var text = cljs.core.get.call(null,map__12422__$1,new cljs.core.Keyword(null,"text","text",-1790561697));return om.core.set_state_BANG_.call(null,owner,new cljs.core.Keyword(null,"text","text",-1790561697),e.target.value);
});
picsou.try$.main_view = (function main_view(app,owner){if(typeof picsou.try$.t12429 !== 'undefined')
{} else
{
/**
* @constructor
*/
picsou.try$.t12429 = (function (owner,app,main_view,meta12430){
this.owner = owner;
this.app = app;
this.main_view = main_view;
this.meta12430 = meta12430;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
picsou.try$.t12429.cljs$lang$type = true;
picsou.try$.t12429.cljs$lang$ctorStr = "picsou.try/t12429";
picsou.try$.t12429.cljs$lang$ctorPrWriter = (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"picsou.try/t12429");
});
picsou.try$.t12429.prototype.om$core$IRenderState$ = true;
picsou.try$.t12429.prototype.om$core$IRenderState$render_state$arity$2 = (function (this$,state){var self__ = this;
var this$__$1 = this;return React.DOM.div(null,om.dom.input.call(null,{"onKeyDown": ((function (this$__$1){
return (function (p1__12424_SHARP_){if(cljs.core._EQ_.call(null,p1__12424_SHARP_.key,"Enter"))
{return picsou.try$.get_parse.call(null,self__.app,self__.owner);
} else
{return null;
}
});})(this$__$1))
, "onChange": ((function (this$__$1){
return (function (p1__12423_SHARP_){return picsou.try$.handle_change.call(null,p1__12423_SHARP_,self__.owner,state);
});})(this$__$1))
, "value": new cljs.core.Keyword(null,"text","text",-1790561697).cljs$core$IFn$_invoke$arity$1(state), "ref": "phrase", "type": new cljs.core.Keyword(null,"text","text",-1790561697)}),React.DOM.button({"onClick": ((function (this$__$1){
return (function (){return picsou.try$.get_parse.call(null,self__.app,self__.owner);
});})(this$__$1))
},"Try me!"));
});
picsou.try$.t12429.prototype.om$core$IWillMount$ = true;
picsou.try$.t12429.prototype.om$core$IWillMount$will_mount$arity$1 = (function (_){var self__ = this;
var ___$1 = this;var temp__4126__auto__ = cljs.core.re_find.call(null,/#(.+)$/,window.location.hash);if(cljs.core.truth_(temp__4126__auto__))
{var vec__12432 = temp__4126__auto__;var ___$2 = cljs.core.nth.call(null,vec__12432,(0),null);var msg_id = cljs.core.nth.call(null,vec__12432,(1),null);return picsou.try$.get_parse.call(null,(function (){var or__3551__auto__ = msg_id;if(cljs.core.truth_(or__3551__auto__))
{return or__3551__auto__;
} else
{return "random";
}
})(),self__.app);
} else
{return null;
}
});
picsou.try$.t12429.prototype.om$core$IInitState$ = true;
picsou.try$.t12429.prototype.om$core$IInitState$init_state$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"text","text",-1790561697),"next week"], null);
});
picsou.try$.t12429.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_12431){var self__ = this;
var _12431__$1 = this;return self__.meta12430;
});
picsou.try$.t12429.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_12431,meta12430__$1){var self__ = this;
var _12431__$1 = this;return (new picsou.try$.t12429(self__.owner,self__.app,self__.main_view,meta12430__$1));
});
picsou.try$.__GT_t12429 = (function __GT_t12429(owner__$1,app__$1,main_view__$1,meta12430){return (new picsou.try$.t12429(owner__$1,app__$1,main_view__$1,meta12430));
});
}
return (new picsou.try$.t12429(owner,app,main_view,null));
});
om.core.root.call(null,picsou.try$.main_view,picsou.try$.app_state,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"target","target",253001721),document.getElementById("main")], null));

//# sourceMappingURL=try.js.map