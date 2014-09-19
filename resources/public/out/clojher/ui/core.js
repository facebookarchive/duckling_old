// Compiled by ClojureScript 0.0-2311
goog.provide('clojher.ui.core');
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
clojher.ui.core.app_state = cljs.core.atom.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"email","email",1415816706),new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"from","from",1815293044),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"email","email",1415816706),"Zoe"], null)], null),new cljs.core.Keyword(null,"to","to",192099007),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"email","email",1415816706),"alex@wit.ai"], null)], null),new cljs.core.Keyword(null,"date","date",-1463434462),"2014-10-08",new cljs.core.Keyword(null,"subject","subject",-1411880451),"Comment \u00E7a va?",new cljs.core.Keyword(null,"plain","plain",1368629269),"0123\n\n6789"], null),new cljs.core.Keyword(null,"meta","meta",1499536964),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"category","category",-593092832),"module",new cljs.core.Keyword(null,"notes","notes",-1039600523),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"start","start",-355208981),(3),new cljs.core.Keyword(null,"end","end",-268185958),(4),new cljs.core.Keyword(null,"class","class",-2030961996),"splitter"], null),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"start","start",-355208981),(6),new cljs.core.Keyword(null,"end","end",-268185958),(7),new cljs.core.Keyword(null,"class","class",-2030961996),"ignore"], null)], null)], null)], null));
clojher.ui.core.meths = new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"get","get",1683182755),"GET",new cljs.core.Keyword(null,"put","put",1299772570),"PUT",new cljs.core.Keyword(null,"post","post",269697687),"POST",new cljs.core.Keyword(null,"delete","delete",-1768633620),"DELETE"], null);
clojher.ui.core.edn_xhr = (function edn_xhr(p__5822){var map__5824 = p__5822;var map__5824__$1 = ((cljs.core.seq_QMARK_.call(null,map__5824))?cljs.core.apply.call(null,cljs.core.hash_map,map__5824):map__5824);var on_complete = cljs.core.get.call(null,map__5824__$1,new cljs.core.Keyword(null,"on-complete","on-complete",-1531183971));var data = cljs.core.get.call(null,map__5824__$1,new cljs.core.Keyword(null,"data","data",-232669377));var url = cljs.core.get.call(null,map__5824__$1,new cljs.core.Keyword(null,"url","url",276297046));var method = cljs.core.get.call(null,map__5824__$1,new cljs.core.Keyword(null,"method","method",55703592));var xhr = (new goog.net.XhrIo());goog.events.listen(xhr,goog.net.EventType.COMPLETE,((function (xhr,map__5824,map__5824__$1,on_complete,data,url,method){
return (function (e){return on_complete.call(null,cljs.reader.read_string.call(null,xhr.getResponseText()));
});})(xhr,map__5824,map__5824__$1,on_complete,data,url,method))
);
return xhr.send(url,clojher.ui.core.meths.call(null,method),(cljs.core.truth_(data)?cljs.core.pr_str.call(null,data):null),{"Content-Type": "application/edn"});
});
clojher.ui.core.get_message = (function get_message(msg_id,app){return clojher.ui.core.edn_xhr.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"method","method",55703592),new cljs.core.Keyword(null,"get","get",1683182755),new cljs.core.Keyword(null,"url","url",276297046),("messages/"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(msg_id)),new cljs.core.Keyword(null,"on-complete","on-complete",-1531183971),(function (p1__5825_SHARP_){om.core.transact_BANG_.call(null,app,new cljs.core.Keyword(null,"email","email",1415816706),cljs.core.constantly.call(null,new cljs.core.Keyword(null,"email","email",1415816706).cljs$core$IFn$_invoke$arity$1(p1__5825_SHARP_)));
om.core.transact_BANG_.call(null,app,new cljs.core.Keyword(null,"meta","meta",1499536964),cljs.core.constantly.call(null,new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(p1__5825_SHARP_)));
return history.pushState(null,null,("#"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"msg-id","msg-id",-1965771869).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"email","email",1415816706).cljs$core$IFn$_invoke$arity$1(p1__5825_SHARP_)))));
})], null));
});
clojher.ui.core.save_meta = (function save_meta(app,meta){cljs.core.print.call(null,"saving meta",meta);
return clojher.ui.core.edn_xhr.call(null,new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"method","method",55703592),new cljs.core.Keyword(null,"put","put",1299772570),new cljs.core.Keyword(null,"url","url",276297046),("messages/"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"msg-id","msg-id",-1965771869).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"email","email",1415816706).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,app))))+"/meta"),new cljs.core.Keyword(null,"data","data",-232669377),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"meta","meta",1499536964),meta], null),new cljs.core.Keyword(null,"on-complete","on-complete",-1531183971),(function (){return om.core.update_BANG_.call(null,app,new cljs.core.Keyword(null,"meta","meta",1499536964),meta);
})], null));
});
clojher.ui.core.segments = (function segments(notes,length){var notes__$1 = cljs.core.filter.call(null,new cljs.core.Keyword(null,"start","start",-355208981),notes);var containers = ((function (notes__$1){
return (function (i_start,i_end){return cljs.core.apply.call(null,cljs.core.str,cljs.core.interpose.call(null," ",cljs.core.map.call(null,new cljs.core.Keyword(null,"class","class",-2030961996),cljs.core.filter.call(null,((function (notes__$1){
return (function (p__5830){var map__5831 = p__5830;var map__5831__$1 = ((cljs.core.seq_QMARK_.call(null,map__5831))?cljs.core.apply.call(null,cljs.core.hash_map,map__5831):map__5831);var end = cljs.core.get.call(null,map__5831__$1,new cljs.core.Keyword(null,"end","end",-268185958));var start = cljs.core.get.call(null,map__5831__$1,new cljs.core.Keyword(null,"start","start",-355208981));return ((start <= i_start)) && (((i_start <= i_end)) && ((i_end <= end)));
});})(notes__$1))
,notes__$1))));
});})(notes__$1))
;var points = cljs.core.sort.call(null,cljs.core.set.call(null,cljs.core.concat.call(null,cljs.core.mapcat.call(null,cljs.core.juxt.call(null,new cljs.core.Keyword(null,"start","start",-355208981),new cljs.core.Keyword(null,"end","end",-268185958)),notes__$1),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),length], null))));var segs = cljs.core.map.call(null,cljs.core.vector,points,cljs.core.next.call(null,points));var res = cljs.core.map.call(null,((function (notes__$1,containers,points,segs){
return (function (p__5832){var vec__5833 = p__5832;var start = cljs.core.nth.call(null,vec__5833,(0),null);var end = cljs.core.nth.call(null,vec__5833,(1),null);return (new cljs.core.PersistentVector(null,3,(5),cljs.core.PersistentVector.EMPTY_NODE,[start,end,containers.call(null,start,end)],null));
});})(notes__$1,containers,points,segs))
,segs);return cljs.core.vec.call(null,res);
});
clojher.ui.core.segments.call(null,new cljs.core.Keyword(null,"notes","notes",-1039600523).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,clojher.ui.core.app_state))),(30));
clojher.ui.core.delete_note = (function delete_note(app,note){return clojher.ui.core.save_meta.call(null,app,cljs.core.update_in.call(null,new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,app)),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"notes","notes",-1039600523)], null),(function (xs){return cljs.core.vec.call(null,cljs.core.remove.call(null,(function (p1__5834_SHARP_){return cljs.core._EQ_.call(null,note,p1__5834_SHARP_);
}),xs));
})));
});
clojher.ui.core.add_note = (function add_note(event,app){var class$ = event.target.id;var sel = window.getSelection();if(cljs.core._EQ_.call(null,"Range",sel.type))
{var range = sel.getRangeAt((0));var parent = range.commonAncestorContainer.parentElement;var span_start = window.parseInt(parent.getAttribute("data-start"));var _ = (cljs.core.truth_(isNaN(span_start))?(function(){throw "Cannot create interleaved notes yet"})():null);var start = (span_start + range.startOffset);var end = (span_start + range.endOffset);return clojher.ui.core.save_meta.call(null,app,cljs.core.update_in.call(null,new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,app)),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"notes","notes",-1039600523)], null),((function (range,parent,span_start,_,start,end,class$,sel){
return (function (p1__5835_SHARP_){return cljs.core.conj.call(null,p1__5835_SHARP_,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"start","start",-355208981),start,new cljs.core.Keyword(null,"end","end",-268185958),end,new cljs.core.Keyword(null,"class","class",-2030961996),class$], null));
});})(range,parent,span_start,_,start,end,class$,sel))
));
} else
{return null;
}
});
clojher.ui.core.span = (function span(app,text,notes,p__5836){var vec__5842 = p__5836;var start = cljs.core.nth.call(null,vec__5842,(0),null);var end = cljs.core.nth.call(null,vec__5842,(1),null);var classes = cljs.core.nth.call(null,vec__5842,(2),null);console.log("span",start,end,classes);
return cljs.core.conj.call(null,cljs.core.vec.call(null,(function (){var iter__4276__auto__ = ((function (vec__5842,start,end,classes){
return (function iter__5843(s__5844){return (new cljs.core.LazySeq(null,((function (vec__5842,start,end,classes){
return (function (){var s__5844__$1 = s__5844;while(true){
var temp__4126__auto__ = cljs.core.seq.call(null,s__5844__$1);if(temp__4126__auto__)
{var s__5844__$2 = temp__4126__auto__;if(cljs.core.chunked_seq_QMARK_.call(null,s__5844__$2))
{var c__4274__auto__ = cljs.core.chunk_first.call(null,s__5844__$2);var size__4275__auto__ = cljs.core.count.call(null,c__4274__auto__);var b__5846 = cljs.core.chunk_buffer.call(null,size__4275__auto__);if((function (){var i__5845 = (0);while(true){
if((i__5845 < size__4275__auto__))
{var n = cljs.core._nth.call(null,c__4274__auto__,i__5845);if(cljs.core._EQ_.call(null,start,new cljs.core.Keyword(null,"start","start",-355208981).cljs$core$IFn$_invoke$arity$1(n)))
{cljs.core.chunk_append.call(null,b__5846,React.DOM.span(null,React.DOM.button({"onClick": ((function (i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (){return clojher.ui.core.delete_note.call(null,app,cljs.core.deref.call(null,n));
});})(i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
},"-"),((cljs.core._EQ_.call(null,"proposed",new cljs.core.Keyword(null,"status","status",-1997798413).cljs$core$IFn$_invoke$arity$1(n)))?React.DOM.button({"onClick": ((function (i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (){return clojher.ui.core.save_meta.call(null,app,cljs.core.update_in.call(null,new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,app)),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"notes","notes",-1039600523)], null),((function (i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (xs){return cljs.core.map.call(null,((function (i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (nn){if(cljs.core._EQ_.call(null,cljs.core.deref.call(null,n),nn))
{return cljs.core.assoc.call(null,nn,new cljs.core.Keyword(null,"status","status",-1997798413),"ok");
} else
{return nn;
}
});})(i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
,xs);
});})(i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
));
});})(i__5845,s__5844__$1,n,c__4274__auto__,size__4275__auto__,b__5846,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
},"OK"):null)));
{
var G__5847 = (i__5845 + (1));
i__5845 = G__5847;
continue;
}
} else
{{
var G__5848 = (i__5845 + (1));
i__5845 = G__5848;
continue;
}
}
} else
{return true;
}
break;
}
})())
{return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__5846),iter__5843.call(null,cljs.core.chunk_rest.call(null,s__5844__$2)));
} else
{return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__5846),null);
}
} else
{var n = cljs.core.first.call(null,s__5844__$2);if(cljs.core._EQ_.call(null,start,new cljs.core.Keyword(null,"start","start",-355208981).cljs$core$IFn$_invoke$arity$1(n)))
{return cljs.core.cons.call(null,React.DOM.span(null,React.DOM.button({"onClick": ((function (s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (){return clojher.ui.core.delete_note.call(null,app,cljs.core.deref.call(null,n));
});})(s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
},"-"),((cljs.core._EQ_.call(null,"proposed",new cljs.core.Keyword(null,"status","status",-1997798413).cljs$core$IFn$_invoke$arity$1(n)))?React.DOM.button({"onClick": ((function (s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (){return clojher.ui.core.save_meta.call(null,app,cljs.core.update_in.call(null,new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,app)),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"notes","notes",-1039600523)], null),((function (s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (xs){return cljs.core.map.call(null,((function (s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes){
return (function (nn){if(cljs.core._EQ_.call(null,cljs.core.deref.call(null,n),nn))
{return cljs.core.assoc.call(null,nn,new cljs.core.Keyword(null,"status","status",-1997798413),"ok");
} else
{return nn;
}
});})(s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
,xs);
});})(s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
));
});})(s__5844__$1,n,s__5844__$2,temp__4126__auto__,vec__5842,start,end,classes))
},"OK"):null)),iter__5843.call(null,cljs.core.rest.call(null,s__5844__$2)));
} else
{{
var G__5849 = cljs.core.rest.call(null,s__5844__$2);
s__5844__$1 = G__5849;
continue;
}
}
}
} else
{return null;
}
break;
}
});})(vec__5842,start,end,classes))
,null,null));
});})(vec__5842,start,end,classes))
;return iter__4276__auto__.call(null,notes);
})()),React.DOM.span({"style": {"white-space": "pre-wrap"}, "data-start": start, "className": classes},cljs.core.subs.call(null,text,start,end)));
});
clojher.ui.core.contacts = (function contacts(xs){return cljs.core.apply.call(null,cljs.core.str,cljs.core.interpose.call(null,", ",cljs.core.map.call(null,(function (p__5852){var map__5853 = p__5852;var map__5853__$1 = ((cljs.core.seq_QMARK_.call(null,map__5853))?cljs.core.apply.call(null,cljs.core.hash_map,map__5853):map__5853);var email = cljs.core.get.call(null,map__5853__$1,new cljs.core.Keyword(null,"email","email",1415816706));var name = cljs.core.get.call(null,map__5853__$1,new cljs.core.Keyword(null,"name","name",1843675177));if(cljs.core.truth_(name))
{return goog.string.format("%s (%s)",name,email);
} else
{return email;
}
}),xs)));
});
clojher.ui.core.contacts_view = (function contacts_view(app,owner){if(typeof clojher.ui.core.t5861 !== 'undefined')
{} else
{
/**
* @constructor
*/
clojher.ui.core.t5861 = (function (owner,app,contacts_view,meta5862){
this.owner = owner;
this.app = app;
this.contacts_view = contacts_view;
this.meta5862 = meta5862;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
clojher.ui.core.t5861.cljs$lang$type = true;
clojher.ui.core.t5861.cljs$lang$ctorStr = "clojher.ui.core/t5861";
clojher.ui.core.t5861.cljs$lang$ctorPrWriter = (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"clojher.ui.core/t5861");
});
clojher.ui.core.t5861.prototype.om$core$IRenderState$ = true;
clojher.ui.core.t5861.prototype.om$core$IRenderState$render_state$arity$2 = (function (this$,p__5864){var self__ = this;
var map__5865 = p__5864;var map__5865__$1 = ((cljs.core.seq_QMARK_.call(null,map__5865))?cljs.core.apply.call(null,cljs.core.hash_map,map__5865):map__5865);var delete$ = cljs.core.get.call(null,map__5865__$1,new cljs.core.Keyword(null,"delete","delete",-1768633620));var this$__$1 = this;var email = new cljs.core.Keyword(null,"email","email",1415816706).cljs$core$IFn$_invoke$arity$1(self__.app);var text = new cljs.core.Keyword(null,"plain","plain",1368629269).cljs$core$IFn$_invoke$arity$1(email);var segs = clojher.ui.core.segments.call(null,new cljs.core.Keyword(null,"notes","notes",-1039600523).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(self__.app)),cljs.core.count.call(null,text));return React.DOM.div(null,React.DOM.div({"id": "controls"},React.DOM.div(null,React.DOM.a({"href": "#", "onClick": ((function (email,text,segs,this$__$1,map__5865,map__5865__$1,delete$){
return (function (){return clojher.ui.core.get_message.call(null,"random",self__.app);
});})(email,text,segs,this$__$1,map__5865,map__5865__$1,delete$))
},"Random"),React.DOM.hr(null),React.DOM.div(null,clojher.ui.core.contacts.call(null,new cljs.core.Keyword(null,"from","from",1815293044).cljs$core$IFn$_invoke$arity$1(email))),React.DOM.div(null,clojher.ui.core.contacts.call(null,new cljs.core.Keyword(null,"to","to",192099007).cljs$core$IFn$_invoke$arity$1(email))),React.DOM.div(null,(''+cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"date","date",-1463434462).cljs$core$IFn$_invoke$arity$1(email)))),React.DOM.div(null,goog.string.format("%s (%s)",new cljs.core.Keyword(null,"subject","subject",-1411880451).cljs$core$IFn$_invoke$arity$1(email),new cljs.core.Keyword(null,"msg-id","msg-id",-1965771869).cljs$core$IFn$_invoke$arity$1(email))),React.DOM.hr(null)),cljs.core.apply.call(null,om.dom.div,{"id": "category"},cljs.core.map.call(null,((function (email,text,segs,this$__$1,map__5865,map__5865__$1,delete$){
return (function (cat){return React.DOM.button({"onClick": ((function (email,text,segs,this$__$1,map__5865,map__5865__$1,delete$){
return (function (){return clojher.ui.core.save_meta.call(null,self__.app,cljs.core.assoc.call(null,new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,self__.app)),new cljs.core.Keyword(null,"category","category",-593092832),cat));
});})(email,text,segs,this$__$1,map__5865,map__5865__$1,delete$))
, "className": ((cljs.core._EQ_.call(null,cat,new cljs.core.Keyword(null,"category","category",-593092832).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(self__.app))))?"selected":null), "id": cat},cat);
});})(email,text,segs,this$__$1,map__5865,map__5865__$1,delete$))
,new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, ["plain","module","ignore","flag"], null))),cljs.core.apply.call(null,om.dom.div,{"id": "notes"},cljs.core.map.call(null,((function (email,text,segs,this$__$1,map__5865,map__5865__$1,delete$){
return (function (note_class){return React.DOM.button({"onClick": ((function (email,text,segs,this$__$1,map__5865,map__5865__$1,delete$){
return (function (p1__5854_SHARP_){return clojher.ui.core.add_note.call(null,p1__5854_SHARP_,self__.app);
});})(email,text,segs,this$__$1,map__5865,map__5865__$1,delete$))
, "id": note_class},note_class);
});})(email,text,segs,this$__$1,map__5865,map__5865__$1,delete$))
,new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, ["splitter","signature","header","ignore"], null)))),cljs.core.apply.call(null,om.dom.div,{"id": "msg-body"},cljs.core.mapcat.call(null,cljs.core.partial.call(null,clojher.ui.core.span,self__.app,text,new cljs.core.Keyword(null,"notes","notes",-1039600523).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"meta","meta",1499536964).cljs$core$IFn$_invoke$arity$1(self__.app))),segs)));
});
clojher.ui.core.t5861.prototype.om$core$IWillMount$ = true;
clojher.ui.core.t5861.prototype.om$core$IWillMount$will_mount$arity$1 = (function (_){var self__ = this;
var ___$1 = this;var vec__5866 = cljs.core.re_find.call(null,/#(.+)$/,window.location.hash);var ___$2 = cljs.core.nth.call(null,vec__5866,(0),null);var msg_id = cljs.core.nth.call(null,vec__5866,(1),null);return clojher.ui.core.get_message.call(null,(function (){var or__3551__auto__ = msg_id;if(cljs.core.truth_(or__3551__auto__))
{return or__3551__auto__;
} else
{return "random";
}
})(),self__.app);
});
clojher.ui.core.t5861.prototype.om$core$IInitState$ = true;
clojher.ui.core.t5861.prototype.om$core$IInitState$init_state$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return cljs.core.PersistentArrayMap.EMPTY;
});
clojher.ui.core.t5861.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_5863){var self__ = this;
var _5863__$1 = this;return self__.meta5862;
});
clojher.ui.core.t5861.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_5863,meta5862__$1){var self__ = this;
var _5863__$1 = this;return (new clojher.ui.core.t5861(self__.owner,self__.app,self__.contacts_view,meta5862__$1));
});
clojher.ui.core.__GT_t5861 = (function __GT_t5861(owner__$1,app__$1,contacts_view__$1,meta5862){return (new clojher.ui.core.t5861(owner__$1,app__$1,contacts_view__$1,meta5862));
});
}
return (new clojher.ui.core.t5861(owner,app,contacts_view,null));
});
om.core.root.call(null,clojher.ui.core.contacts_view,clojher.ui.core.app_state,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"target","target",253001721),document.getElementById("contacts")], null));

//# sourceMappingURL=core.js.map