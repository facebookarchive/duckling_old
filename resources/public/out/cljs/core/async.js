// Compiled by ClojureScript 0.0-2311
goog.provide('cljs.core.async');
goog.require('cljs.core');
goog.require('cljs.core.async.impl.channels');
goog.require('cljs.core.async.impl.dispatch');
goog.require('cljs.core.async.impl.ioc_helpers');
goog.require('cljs.core.async.impl.protocols');
goog.require('cljs.core.async.impl.channels');
goog.require('cljs.core.async.impl.buffers');
goog.require('cljs.core.async.impl.protocols');
goog.require('cljs.core.async.impl.timers');
goog.require('cljs.core.async.impl.dispatch');
goog.require('cljs.core.async.impl.ioc_helpers');
goog.require('cljs.core.async.impl.buffers');
goog.require('cljs.core.async.impl.timers');
cljs.core.async.fn_handler = (function fn_handler(f){if(typeof cljs.core.async.t9444 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t9444 = (function (f,fn_handler,meta9445){
this.f = f;
this.fn_handler = fn_handler;
this.meta9445 = meta9445;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t9444.cljs$lang$type = true;
cljs.core.async.t9444.cljs$lang$ctorStr = "cljs.core.async/t9444";
cljs.core.async.t9444.cljs$lang$ctorPrWriter = (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t9444");
});
cljs.core.async.t9444.prototype.cljs$core$async$impl$protocols$Handler$ = true;
cljs.core.async.t9444.prototype.cljs$core$async$impl$protocols$Handler$active_QMARK_$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return true;
});
cljs.core.async.t9444.prototype.cljs$core$async$impl$protocols$Handler$commit$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return self__.f;
});
cljs.core.async.t9444.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_9446){var self__ = this;
var _9446__$1 = this;return self__.meta9445;
});
cljs.core.async.t9444.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_9446,meta9445__$1){var self__ = this;
var _9446__$1 = this;return (new cljs.core.async.t9444(self__.f,self__.fn_handler,meta9445__$1));
});
cljs.core.async.__GT_t9444 = (function __GT_t9444(f__$1,fn_handler__$1,meta9445){return (new cljs.core.async.t9444(f__$1,fn_handler__$1,meta9445));
});
}
return (new cljs.core.async.t9444(f,fn_handler,null));
});
/**
* Returns a fixed buffer of size n. When full, puts will block/park.
*/
cljs.core.async.buffer = (function buffer(n){return cljs.core.async.impl.buffers.fixed_buffer.call(null,n);
});
/**
* Returns a buffer of size n. When full, puts will complete but
* val will be dropped (no transfer).
*/
cljs.core.async.dropping_buffer = (function dropping_buffer(n){return cljs.core.async.impl.buffers.dropping_buffer.call(null,n);
});
/**
* Returns a buffer of size n. When full, puts will complete, and be
* buffered, but oldest elements in buffer will be dropped (not
* transferred).
*/
cljs.core.async.sliding_buffer = (function sliding_buffer(n){return cljs.core.async.impl.buffers.sliding_buffer.call(null,n);
});
/**
* Returns true if a channel created with buff will never block. That is to say,
* puts into this buffer will never cause the buffer to be full.
*/
cljs.core.async.unblocking_buffer_QMARK_ = (function unblocking_buffer_QMARK_(buff){var G__9448 = buff;if(G__9448)
{var bit__4201__auto__ = null;if(cljs.core.truth_((function (){var or__3551__auto__ = bit__4201__auto__;if(cljs.core.truth_(or__3551__auto__))
{return or__3551__auto__;
} else
{return G__9448.cljs$core$async$impl$protocols$UnblockingBuffer$;
}
})()))
{return true;
} else
{if((!G__9448.cljs$lang$protocol_mask$partition$))
{return cljs.core.native_satisfies_QMARK_.call(null,cljs.core.async.impl.protocols.UnblockingBuffer,G__9448);
} else
{return false;
}
}
} else
{return cljs.core.native_satisfies_QMARK_.call(null,cljs.core.async.impl.protocols.UnblockingBuffer,G__9448);
}
});
/**
* Creates a channel with an optional buffer. If buf-or-n is a number,
* will create and use a fixed buffer of that size.
*/
cljs.core.async.chan = (function() {
var chan = null;
var chan__0 = (function (){return chan.call(null,null);
});
var chan__1 = (function (buf_or_n){var buf_or_n__$1 = ((cljs.core._EQ_.call(null,buf_or_n,(0)))?null:buf_or_n);return cljs.core.async.impl.channels.chan.call(null,((typeof buf_or_n__$1 === 'number')?cljs.core.async.buffer.call(null,buf_or_n__$1):buf_or_n__$1));
});
chan = function(buf_or_n){
switch(arguments.length){
case 0:
return chan__0.call(this);
case 1:
return chan__1.call(this,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
chan.cljs$core$IFn$_invoke$arity$0 = chan__0;
chan.cljs$core$IFn$_invoke$arity$1 = chan__1;
return chan;
})()
;
/**
* Returns a channel that will close after msecs
*/
cljs.core.async.timeout = (function timeout(msecs){return cljs.core.async.impl.timers.timeout.call(null,msecs);
});
/**
* takes a val from port. Must be called inside a (go ...) block. Will
* return nil if closed. Will park if nothing is available.
*/
cljs.core.async._LT__BANG_ = (function _LT__BANG_(port){throw (new Error(("Assert failed: <! used not in (go ...) block\n"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.call(null,null)))));

});
/**
* Asynchronously takes a val from port, passing to fn1. Will pass nil
* if closed. If on-caller? (default true) is true, and value is
* immediately available, will call fn1 on calling thread.
* Returns nil.
*/
cljs.core.async.take_BANG_ = (function() {
var take_BANG_ = null;
var take_BANG___2 = (function (port,fn1){return take_BANG_.call(null,port,fn1,true);
});
var take_BANG___3 = (function (port,fn1,on_caller_QMARK_){var ret = cljs.core.async.impl.protocols.take_BANG_.call(null,port,cljs.core.async.fn_handler.call(null,fn1));if(cljs.core.truth_(ret))
{var val_9449 = cljs.core.deref.call(null,ret);if(cljs.core.truth_(on_caller_QMARK_))
{fn1.call(null,val_9449);
} else
{cljs.core.async.impl.dispatch.run.call(null,((function (val_9449,ret){
return (function (){return fn1.call(null,val_9449);
});})(val_9449,ret))
);
}
} else
{}
return null;
});
take_BANG_ = function(port,fn1,on_caller_QMARK_){
switch(arguments.length){
case 2:
return take_BANG___2.call(this,port,fn1);
case 3:
return take_BANG___3.call(this,port,fn1,on_caller_QMARK_);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
take_BANG_.cljs$core$IFn$_invoke$arity$2 = take_BANG___2;
take_BANG_.cljs$core$IFn$_invoke$arity$3 = take_BANG___3;
return take_BANG_;
})()
;
cljs.core.async.nop = (function nop(){return null;
});
/**
* puts a val into port. nil values are not allowed. Must be called
* inside a (go ...) block. Will park if no buffer space is available.
*/
cljs.core.async._GT__BANG_ = (function _GT__BANG_(port,val){throw (new Error(("Assert failed: >! used not in (go ...) block\n"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.call(null,null)))));

});
/**
* Asynchronously puts a val into port, calling fn0 (if supplied) when
* complete. nil values are not allowed. Will throw if closed. If
* on-caller? (default true) is true, and the put is immediately
* accepted, will call fn0 on calling thread.  Returns nil.
*/
cljs.core.async.put_BANG_ = (function() {
var put_BANG_ = null;
var put_BANG___2 = (function (port,val){return put_BANG_.call(null,port,val,cljs.core.async.nop);
});
var put_BANG___3 = (function (port,val,fn0){return put_BANG_.call(null,port,val,fn0,true);
});
var put_BANG___4 = (function (port,val,fn0,on_caller_QMARK_){var ret = cljs.core.async.impl.protocols.put_BANG_.call(null,port,val,cljs.core.async.fn_handler.call(null,fn0));if(cljs.core.truth_((function (){var and__3539__auto__ = ret;if(cljs.core.truth_(and__3539__auto__))
{return cljs.core.not_EQ_.call(null,fn0,cljs.core.async.nop);
} else
{return and__3539__auto__;
}
})()))
{if(cljs.core.truth_(on_caller_QMARK_))
{fn0.call(null);
} else
{cljs.core.async.impl.dispatch.run.call(null,fn0);
}
} else
{}
return null;
});
put_BANG_ = function(port,val,fn0,on_caller_QMARK_){
switch(arguments.length){
case 2:
return put_BANG___2.call(this,port,val);
case 3:
return put_BANG___3.call(this,port,val,fn0);
case 4:
return put_BANG___4.call(this,port,val,fn0,on_caller_QMARK_);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
put_BANG_.cljs$core$IFn$_invoke$arity$2 = put_BANG___2;
put_BANG_.cljs$core$IFn$_invoke$arity$3 = put_BANG___3;
put_BANG_.cljs$core$IFn$_invoke$arity$4 = put_BANG___4;
return put_BANG_;
})()
;
cljs.core.async.close_BANG_ = (function close_BANG_(port){return cljs.core.async.impl.protocols.close_BANG_.call(null,port);
});
cljs.core.async.random_array = (function random_array(n){var a = (new Array(n));var n__4407__auto___9450 = n;var x_9451 = (0);while(true){
if((x_9451 < n__4407__auto___9450))
{(a[x_9451] = (0));
{
var G__9452 = (x_9451 + (1));
x_9451 = G__9452;
continue;
}
} else
{}
break;
}
var i = (1);while(true){
if(cljs.core._EQ_.call(null,i,n))
{return a;
} else
{var j = cljs.core.rand_int.call(null,i);(a[i] = (a[j]));
(a[j] = i);
{
var G__9453 = (i + (1));
i = G__9453;
continue;
}
}
break;
}
});
cljs.core.async.alt_flag = (function alt_flag(){var flag = cljs.core.atom.call(null,true);if(typeof cljs.core.async.t9457 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t9457 = (function (flag,alt_flag,meta9458){
this.flag = flag;
this.alt_flag = alt_flag;
this.meta9458 = meta9458;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t9457.cljs$lang$type = true;
cljs.core.async.t9457.cljs$lang$ctorStr = "cljs.core.async/t9457";
cljs.core.async.t9457.cljs$lang$ctorPrWriter = ((function (flag){
return (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t9457");
});})(flag))
;
cljs.core.async.t9457.prototype.cljs$core$async$impl$protocols$Handler$ = true;
cljs.core.async.t9457.prototype.cljs$core$async$impl$protocols$Handler$active_QMARK_$arity$1 = ((function (flag){
return (function (_){var self__ = this;
var ___$1 = this;return cljs.core.deref.call(null,self__.flag);
});})(flag))
;
cljs.core.async.t9457.prototype.cljs$core$async$impl$protocols$Handler$commit$arity$1 = ((function (flag){
return (function (_){var self__ = this;
var ___$1 = this;cljs.core.reset_BANG_.call(null,self__.flag,null);
return true;
});})(flag))
;
cljs.core.async.t9457.prototype.cljs$core$IMeta$_meta$arity$1 = ((function (flag){
return (function (_9459){var self__ = this;
var _9459__$1 = this;return self__.meta9458;
});})(flag))
;
cljs.core.async.t9457.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = ((function (flag){
return (function (_9459,meta9458__$1){var self__ = this;
var _9459__$1 = this;return (new cljs.core.async.t9457(self__.flag,self__.alt_flag,meta9458__$1));
});})(flag))
;
cljs.core.async.__GT_t9457 = ((function (flag){
return (function __GT_t9457(flag__$1,alt_flag__$1,meta9458){return (new cljs.core.async.t9457(flag__$1,alt_flag__$1,meta9458));
});})(flag))
;
}
return (new cljs.core.async.t9457(flag,alt_flag,null));
});
cljs.core.async.alt_handler = (function alt_handler(flag,cb){if(typeof cljs.core.async.t9463 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t9463 = (function (cb,flag,alt_handler,meta9464){
this.cb = cb;
this.flag = flag;
this.alt_handler = alt_handler;
this.meta9464 = meta9464;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t9463.cljs$lang$type = true;
cljs.core.async.t9463.cljs$lang$ctorStr = "cljs.core.async/t9463";
cljs.core.async.t9463.cljs$lang$ctorPrWriter = (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t9463");
});
cljs.core.async.t9463.prototype.cljs$core$async$impl$protocols$Handler$ = true;
cljs.core.async.t9463.prototype.cljs$core$async$impl$protocols$Handler$active_QMARK_$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.active_QMARK_.call(null,self__.flag);
});
cljs.core.async.t9463.prototype.cljs$core$async$impl$protocols$Handler$commit$arity$1 = (function (_){var self__ = this;
var ___$1 = this;cljs.core.async.impl.protocols.commit.call(null,self__.flag);
return self__.cb;
});
cljs.core.async.t9463.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_9465){var self__ = this;
var _9465__$1 = this;return self__.meta9464;
});
cljs.core.async.t9463.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_9465,meta9464__$1){var self__ = this;
var _9465__$1 = this;return (new cljs.core.async.t9463(self__.cb,self__.flag,self__.alt_handler,meta9464__$1));
});
cljs.core.async.__GT_t9463 = (function __GT_t9463(cb__$1,flag__$1,alt_handler__$1,meta9464){return (new cljs.core.async.t9463(cb__$1,flag__$1,alt_handler__$1,meta9464));
});
}
return (new cljs.core.async.t9463(cb,flag,alt_handler,null));
});
/**
* returns derefable [val port] if immediate, nil if enqueued
*/
cljs.core.async.do_alts = (function do_alts(fret,ports,opts){var flag = cljs.core.async.alt_flag.call(null);var n = cljs.core.count.call(null,ports);var idxs = cljs.core.async.random_array.call(null,n);var priority = new cljs.core.Keyword(null,"priority","priority",1431093715).cljs$core$IFn$_invoke$arity$1(opts);var ret = (function (){var i = (0);while(true){
if((i < n))
{var idx = (cljs.core.truth_(priority)?i:(idxs[i]));var port = cljs.core.nth.call(null,ports,idx);var wport = ((cljs.core.vector_QMARK_.call(null,port))?port.call(null,(0)):null);var vbox = (cljs.core.truth_(wport)?(function (){var val = port.call(null,(1));return cljs.core.async.impl.protocols.put_BANG_.call(null,wport,val,cljs.core.async.alt_handler.call(null,flag,((function (i,val,idx,port,wport,flag,n,idxs,priority){
return (function (){return fret.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,wport], null));
});})(i,val,idx,port,wport,flag,n,idxs,priority))
));
})():cljs.core.async.impl.protocols.take_BANG_.call(null,port,cljs.core.async.alt_handler.call(null,flag,((function (i,idx,port,wport,flag,n,idxs,priority){
return (function (p1__9466_SHARP_){return fret.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [p1__9466_SHARP_,port], null));
});})(i,idx,port,wport,flag,n,idxs,priority))
)));if(cljs.core.truth_(vbox))
{return cljs.core.async.impl.channels.box.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.deref.call(null,vbox),(function (){var or__3551__auto__ = wport;if(cljs.core.truth_(or__3551__auto__))
{return or__3551__auto__;
} else
{return port;
}
})()], null));
} else
{{
var G__9467 = (i + (1));
i = G__9467;
continue;
}
}
} else
{return null;
}
break;
}
})();var or__3551__auto__ = ret;if(cljs.core.truth_(or__3551__auto__))
{return or__3551__auto__;
} else
{if(cljs.core.contains_QMARK_.call(null,opts,new cljs.core.Keyword(null,"default","default",-1987822328)))
{var temp__4126__auto__ = (function (){var and__3539__auto__ = cljs.core.async.impl.protocols.active_QMARK_.call(null,flag);if(cljs.core.truth_(and__3539__auto__))
{return cljs.core.async.impl.protocols.commit.call(null,flag);
} else
{return and__3539__auto__;
}
})();if(cljs.core.truth_(temp__4126__auto__))
{var got = temp__4126__auto__;return cljs.core.async.impl.channels.box.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"default","default",-1987822328).cljs$core$IFn$_invoke$arity$1(opts),new cljs.core.Keyword(null,"default","default",-1987822328)], null));
} else
{return null;
}
} else
{return null;
}
}
});
/**
* Completes at most one of several channel operations. Must be called
* inside a (go ...) block. ports is a vector of channel endpoints, which
* can be either a channel to take from or a vector of
* [channel-to-put-to val-to-put], in any combination. Takes will be
* made as if by <!, and puts will be made as if by >!. Unless
* the :priority option is true, if more than one port operation is
* ready a non-deterministic choice will be made. If no operation is
* ready and a :default value is supplied, [default-val :default] will
* be returned, otherwise alts! will park until the first operation to
* become ready completes. Returns [val port] of the completed
* operation, where val is the value taken for takes, and nil for puts.
* 
* opts are passed as :key val ... Supported options:
* 
* :default val - the value to use if none of the operations are immediately ready
* :priority true - (default nil) when true, the operations will be tried in order.
* 
* Note: there is no guarantee that the port exps or val exprs will be
* used, nor in what order should they be, so they should not be
* depended upon for side effects.
* @param {...*} var_args
*/
cljs.core.async.alts_BANG_ = (function() { 
var alts_BANG___delegate = function (ports,p__9468){var map__9470 = p__9468;var map__9470__$1 = ((cljs.core.seq_QMARK_.call(null,map__9470))?cljs.core.apply.call(null,cljs.core.hash_map,map__9470):map__9470);var opts = map__9470__$1;throw (new Error(("Assert failed: alts! used not in (go ...) block\n"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.call(null,null)))));

};
var alts_BANG_ = function (ports,var_args){
var p__9468 = null;if (arguments.length > 1) {
  p__9468 = cljs.core.array_seq(Array.prototype.slice.call(arguments, 1),0);} 
return alts_BANG___delegate.call(this,ports,p__9468);};
alts_BANG_.cljs$lang$maxFixedArity = 1;
alts_BANG_.cljs$lang$applyTo = (function (arglist__9471){
var ports = cljs.core.first(arglist__9471);
var p__9468 = cljs.core.rest(arglist__9471);
return alts_BANG___delegate(ports,p__9468);
});
alts_BANG_.cljs$core$IFn$_invoke$arity$variadic = alts_BANG___delegate;
return alts_BANG_;
})()
;
/**
* Takes a function and a source channel, and returns a channel which
* contains the values produced by applying f to each value taken from
* the source channel
*/
cljs.core.async.map_LT_ = (function map_LT_(f,ch){if(typeof cljs.core.async.t9479 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t9479 = (function (ch,f,map_LT_,meta9480){
this.ch = ch;
this.f = f;
this.map_LT_ = map_LT_;
this.meta9480 = meta9480;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t9479.cljs$lang$type = true;
cljs.core.async.t9479.cljs$lang$ctorStr = "cljs.core.async/t9479";
cljs.core.async.t9479.cljs$lang$ctorPrWriter = (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t9479");
});
cljs.core.async.t9479.prototype.cljs$core$async$impl$protocols$WritePort$ = true;
cljs.core.async.t9479.prototype.cljs$core$async$impl$protocols$WritePort$put_BANG_$arity$3 = (function (_,val,fn0){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.put_BANG_.call(null,self__.ch,val,fn0);
});
cljs.core.async.t9479.prototype.cljs$core$async$impl$protocols$ReadPort$ = true;
cljs.core.async.t9479.prototype.cljs$core$async$impl$protocols$ReadPort$take_BANG_$arity$2 = (function (_,fn1){var self__ = this;
var ___$1 = this;var ret = cljs.core.async.impl.protocols.take_BANG_.call(null,self__.ch,(function (){if(typeof cljs.core.async.t9482 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t9482 = (function (fn1,_,meta9480,ch,f,map_LT_,meta9483){
this.fn1 = fn1;
this._ = _;
this.meta9480 = meta9480;
this.ch = ch;
this.f = f;
this.map_LT_ = map_LT_;
this.meta9483 = meta9483;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t9482.cljs$lang$type = true;
cljs.core.async.t9482.cljs$lang$ctorStr = "cljs.core.async/t9482";
cljs.core.async.t9482.cljs$lang$ctorPrWriter = ((function (___$1){
return (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t9482");
});})(___$1))
;
cljs.core.async.t9482.prototype.cljs$core$async$impl$protocols$Handler$ = true;
cljs.core.async.t9482.prototype.cljs$core$async$impl$protocols$Handler$active_QMARK_$arity$1 = ((function (___$1){
return (function (___$3){var self__ = this;
var ___$4 = this;return cljs.core.async.impl.protocols.active_QMARK_.call(null,self__.fn1);
});})(___$1))
;
cljs.core.async.t9482.prototype.cljs$core$async$impl$protocols$Handler$lock_id$arity$1 = ((function (___$1){
return (function (___$3){var self__ = this;
var ___$4 = this;return cljs.core.async.impl.protocols.lock_id.call(null,self__.fn1);
});})(___$1))
;
cljs.core.async.t9482.prototype.cljs$core$async$impl$protocols$Handler$commit$arity$1 = ((function (___$1){
return (function (___$3){var self__ = this;
var ___$4 = this;var f1 = cljs.core.async.impl.protocols.commit.call(null,self__.fn1);return ((function (f1,___$4,___$1){
return (function (p1__9472_SHARP_){return f1.call(null,(((p1__9472_SHARP_ == null))?null:self__.f.call(null,p1__9472_SHARP_)));
});
;})(f1,___$4,___$1))
});})(___$1))
;
cljs.core.async.t9482.prototype.cljs$core$IMeta$_meta$arity$1 = ((function (___$1){
return (function (_9484){var self__ = this;
var _9484__$1 = this;return self__.meta9483;
});})(___$1))
;
cljs.core.async.t9482.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = ((function (___$1){
return (function (_9484,meta9483__$1){var self__ = this;
var _9484__$1 = this;return (new cljs.core.async.t9482(self__.fn1,self__._,self__.meta9480,self__.ch,self__.f,self__.map_LT_,meta9483__$1));
});})(___$1))
;
cljs.core.async.__GT_t9482 = ((function (___$1){
return (function __GT_t9482(fn1__$1,___$2,meta9480__$1,ch__$2,f__$2,map_LT___$2,meta9483){return (new cljs.core.async.t9482(fn1__$1,___$2,meta9480__$1,ch__$2,f__$2,map_LT___$2,meta9483));
});})(___$1))
;
}
return (new cljs.core.async.t9482(fn1,___$1,self__.meta9480,self__.ch,self__.f,self__.map_LT_,null));
})());if(cljs.core.truth_((function (){var and__3539__auto__ = ret;if(cljs.core.truth_(and__3539__auto__))
{return !((cljs.core.deref.call(null,ret) == null));
} else
{return and__3539__auto__;
}
})()))
{return cljs.core.async.impl.channels.box.call(null,self__.f.call(null,cljs.core.deref.call(null,ret)));
} else
{return ret;
}
});
cljs.core.async.t9479.prototype.cljs$core$async$impl$protocols$Channel$ = true;
cljs.core.async.t9479.prototype.cljs$core$async$impl$protocols$Channel$close_BANG_$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.close_BANG_.call(null,self__.ch);
});
cljs.core.async.t9479.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_9481){var self__ = this;
var _9481__$1 = this;return self__.meta9480;
});
cljs.core.async.t9479.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_9481,meta9480__$1){var self__ = this;
var _9481__$1 = this;return (new cljs.core.async.t9479(self__.ch,self__.f,self__.map_LT_,meta9480__$1));
});
cljs.core.async.__GT_t9479 = (function __GT_t9479(ch__$1,f__$1,map_LT___$1,meta9480){return (new cljs.core.async.t9479(ch__$1,f__$1,map_LT___$1,meta9480));
});
}
return (new cljs.core.async.t9479(ch,f,map_LT_,null));
});
/**
* Takes a function and a target channel, and returns a channel which
* applies f to each value before supplying it to the target channel.
*/
cljs.core.async.map_GT_ = (function map_GT_(f,ch){if(typeof cljs.core.async.t9488 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t9488 = (function (ch,f,map_GT_,meta9489){
this.ch = ch;
this.f = f;
this.map_GT_ = map_GT_;
this.meta9489 = meta9489;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t9488.cljs$lang$type = true;
cljs.core.async.t9488.cljs$lang$ctorStr = "cljs.core.async/t9488";
cljs.core.async.t9488.cljs$lang$ctorPrWriter = (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t9488");
});
cljs.core.async.t9488.prototype.cljs$core$async$impl$protocols$WritePort$ = true;
cljs.core.async.t9488.prototype.cljs$core$async$impl$protocols$WritePort$put_BANG_$arity$3 = (function (_,val,fn0){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.put_BANG_.call(null,self__.ch,self__.f.call(null,val),fn0);
});
cljs.core.async.t9488.prototype.cljs$core$async$impl$protocols$ReadPort$ = true;
cljs.core.async.t9488.prototype.cljs$core$async$impl$protocols$ReadPort$take_BANG_$arity$2 = (function (_,fn1){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.take_BANG_.call(null,self__.ch,fn1);
});
cljs.core.async.t9488.prototype.cljs$core$async$impl$protocols$Channel$ = true;
cljs.core.async.t9488.prototype.cljs$core$async$impl$protocols$Channel$close_BANG_$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.close_BANG_.call(null,self__.ch);
});
cljs.core.async.t9488.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_9490){var self__ = this;
var _9490__$1 = this;return self__.meta9489;
});
cljs.core.async.t9488.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_9490,meta9489__$1){var self__ = this;
var _9490__$1 = this;return (new cljs.core.async.t9488(self__.ch,self__.f,self__.map_GT_,meta9489__$1));
});
cljs.core.async.__GT_t9488 = (function __GT_t9488(ch__$1,f__$1,map_GT___$1,meta9489){return (new cljs.core.async.t9488(ch__$1,f__$1,map_GT___$1,meta9489));
});
}
return (new cljs.core.async.t9488(ch,f,map_GT_,null));
});
/**
* Takes a predicate and a target channel, and returns a channel which
* supplies only the values for which the predicate returns true to the
* target channel.
*/
cljs.core.async.filter_GT_ = (function filter_GT_(p,ch){if(typeof cljs.core.async.t9494 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t9494 = (function (ch,p,filter_GT_,meta9495){
this.ch = ch;
this.p = p;
this.filter_GT_ = filter_GT_;
this.meta9495 = meta9495;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t9494.cljs$lang$type = true;
cljs.core.async.t9494.cljs$lang$ctorStr = "cljs.core.async/t9494";
cljs.core.async.t9494.cljs$lang$ctorPrWriter = (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t9494");
});
cljs.core.async.t9494.prototype.cljs$core$async$impl$protocols$WritePort$ = true;
cljs.core.async.t9494.prototype.cljs$core$async$impl$protocols$WritePort$put_BANG_$arity$3 = (function (_,val,fn0){var self__ = this;
var ___$1 = this;if(cljs.core.truth_(self__.p.call(null,val)))
{return cljs.core.async.impl.protocols.put_BANG_.call(null,self__.ch,val,fn0);
} else
{return cljs.core.async.impl.channels.box.call(null,null);
}
});
cljs.core.async.t9494.prototype.cljs$core$async$impl$protocols$ReadPort$ = true;
cljs.core.async.t9494.prototype.cljs$core$async$impl$protocols$ReadPort$take_BANG_$arity$2 = (function (_,fn1){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.take_BANG_.call(null,self__.ch,fn1);
});
cljs.core.async.t9494.prototype.cljs$core$async$impl$protocols$Channel$ = true;
cljs.core.async.t9494.prototype.cljs$core$async$impl$protocols$Channel$close_BANG_$arity$1 = (function (_){var self__ = this;
var ___$1 = this;return cljs.core.async.impl.protocols.close_BANG_.call(null,self__.ch);
});
cljs.core.async.t9494.prototype.cljs$core$IMeta$_meta$arity$1 = (function (_9496){var self__ = this;
var _9496__$1 = this;return self__.meta9495;
});
cljs.core.async.t9494.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (_9496,meta9495__$1){var self__ = this;
var _9496__$1 = this;return (new cljs.core.async.t9494(self__.ch,self__.p,self__.filter_GT_,meta9495__$1));
});
cljs.core.async.__GT_t9494 = (function __GT_t9494(ch__$1,p__$1,filter_GT___$1,meta9495){return (new cljs.core.async.t9494(ch__$1,p__$1,filter_GT___$1,meta9495));
});
}
return (new cljs.core.async.t9494(ch,p,filter_GT_,null));
});
/**
* Takes a predicate and a target channel, and returns a channel which
* supplies only the values for which the predicate returns false to the
* target channel.
*/
cljs.core.async.remove_GT_ = (function remove_GT_(p,ch){return cljs.core.async.filter_GT_.call(null,cljs.core.complement.call(null,p),ch);
});
/**
* Takes a predicate and a source channel, and returns a channel which
* contains only the values taken from the source channel for which the
* predicate returns true. The returned channel will be unbuffered by
* default, or a buf-or-n can be supplied. The channel will close
* when the source channel closes.
*/
cljs.core.async.filter_LT_ = (function() {
var filter_LT_ = null;
var filter_LT___2 = (function (p,ch){return filter_LT_.call(null,p,ch,null);
});
var filter_LT___3 = (function (p,ch,buf_or_n){var out = cljs.core.async.chan.call(null,buf_or_n);var c__6353__auto___9579 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___9579,out){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___9579,out){
return (function (state_9558){var state_val_9559 = (state_9558[(1)]);if((state_val_9559 === (7)))
{var inst_9554 = (state_9558[(2)]);var state_9558__$1 = state_9558;var statearr_9560_9580 = state_9558__$1;(statearr_9560_9580[(2)] = inst_9554);
(statearr_9560_9580[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (1)))
{var state_9558__$1 = state_9558;var statearr_9561_9581 = state_9558__$1;(statearr_9561_9581[(2)] = null);
(statearr_9561_9581[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (4)))
{var inst_9540 = (state_9558[(7)]);var inst_9540__$1 = (state_9558[(2)]);var inst_9541 = (inst_9540__$1 == null);var state_9558__$1 = (function (){var statearr_9562 = state_9558;(statearr_9562[(7)] = inst_9540__$1);
return statearr_9562;
})();if(cljs.core.truth_(inst_9541))
{var statearr_9563_9582 = state_9558__$1;(statearr_9563_9582[(1)] = (5));
} else
{var statearr_9564_9583 = state_9558__$1;(statearr_9564_9583[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (6)))
{var inst_9540 = (state_9558[(7)]);var inst_9545 = p.call(null,inst_9540);var state_9558__$1 = state_9558;if(cljs.core.truth_(inst_9545))
{var statearr_9565_9584 = state_9558__$1;(statearr_9565_9584[(1)] = (8));
} else
{var statearr_9566_9585 = state_9558__$1;(statearr_9566_9585[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (3)))
{var inst_9556 = (state_9558[(2)]);var state_9558__$1 = state_9558;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_9558__$1,inst_9556);
} else
{if((state_val_9559 === (2)))
{var state_9558__$1 = state_9558;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_9558__$1,(4),ch);
} else
{if((state_val_9559 === (11)))
{var inst_9548 = (state_9558[(2)]);var state_9558__$1 = state_9558;var statearr_9567_9586 = state_9558__$1;(statearr_9567_9586[(2)] = inst_9548);
(statearr_9567_9586[(1)] = (10));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (9)))
{var state_9558__$1 = state_9558;var statearr_9568_9587 = state_9558__$1;(statearr_9568_9587[(2)] = null);
(statearr_9568_9587[(1)] = (10));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (5)))
{var inst_9543 = cljs.core.async.close_BANG_.call(null,out);var state_9558__$1 = state_9558;var statearr_9569_9588 = state_9558__$1;(statearr_9569_9588[(2)] = inst_9543);
(statearr_9569_9588[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (10)))
{var inst_9551 = (state_9558[(2)]);var state_9558__$1 = (function (){var statearr_9570 = state_9558;(statearr_9570[(8)] = inst_9551);
return statearr_9570;
})();var statearr_9571_9589 = state_9558__$1;(statearr_9571_9589[(2)] = null);
(statearr_9571_9589[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9559 === (8)))
{var inst_9540 = (state_9558[(7)]);var state_9558__$1 = state_9558;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_9558__$1,(11),out,inst_9540);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___9579,out))
;return ((function (switch__6338__auto__,c__6353__auto___9579,out){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_9575 = [null,null,null,null,null,null,null,null,null];(statearr_9575[(0)] = state_machine__6339__auto__);
(statearr_9575[(1)] = (1));
return statearr_9575;
});
var state_machine__6339__auto____1 = (function (state_9558){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_9558);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e9576){if((e9576 instanceof Object))
{var ex__6342__auto__ = e9576;var statearr_9577_9590 = state_9558;(statearr_9577_9590[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_9558);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e9576;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__9591 = state_9558;
state_9558 = G__9591;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_9558){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_9558);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___9579,out))
})();var state__6355__auto__ = (function (){var statearr_9578 = f__6354__auto__.call(null);(statearr_9578[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___9579);
return statearr_9578;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___9579,out))
);
return out;
});
filter_LT_ = function(p,ch,buf_or_n){
switch(arguments.length){
case 2:
return filter_LT___2.call(this,p,ch);
case 3:
return filter_LT___3.call(this,p,ch,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
filter_LT_.cljs$core$IFn$_invoke$arity$2 = filter_LT___2;
filter_LT_.cljs$core$IFn$_invoke$arity$3 = filter_LT___3;
return filter_LT_;
})()
;
/**
* Takes a predicate and a source channel, and returns a channel which
* contains only the values taken from the source channel for which the
* predicate returns false. The returned channel will be unbuffered by
* default, or a buf-or-n can be supplied. The channel will close
* when the source channel closes.
*/
cljs.core.async.remove_LT_ = (function() {
var remove_LT_ = null;
var remove_LT___2 = (function (p,ch){return remove_LT_.call(null,p,ch,null);
});
var remove_LT___3 = (function (p,ch,buf_or_n){return cljs.core.async.filter_LT_.call(null,cljs.core.complement.call(null,p),ch,buf_or_n);
});
remove_LT_ = function(p,ch,buf_or_n){
switch(arguments.length){
case 2:
return remove_LT___2.call(this,p,ch);
case 3:
return remove_LT___3.call(this,p,ch,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
remove_LT_.cljs$core$IFn$_invoke$arity$2 = remove_LT___2;
remove_LT_.cljs$core$IFn$_invoke$arity$3 = remove_LT___3;
return remove_LT_;
})()
;
cljs.core.async.mapcat_STAR_ = (function mapcat_STAR_(f,in$,out){var c__6353__auto__ = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto__){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto__){
return (function (state_9743){var state_val_9744 = (state_9743[(1)]);if((state_val_9744 === (7)))
{var inst_9739 = (state_9743[(2)]);var state_9743__$1 = state_9743;var statearr_9745_9782 = state_9743__$1;(statearr_9745_9782[(2)] = inst_9739);
(statearr_9745_9782[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (20)))
{var inst_9714 = (state_9743[(7)]);var inst_9725 = (state_9743[(2)]);var inst_9726 = cljs.core.next.call(null,inst_9714);var inst_9700 = inst_9726;var inst_9701 = null;var inst_9702 = (0);var inst_9703 = (0);var state_9743__$1 = (function (){var statearr_9746 = state_9743;(statearr_9746[(8)] = inst_9702);
(statearr_9746[(9)] = inst_9700);
(statearr_9746[(10)] = inst_9725);
(statearr_9746[(11)] = inst_9703);
(statearr_9746[(12)] = inst_9701);
return statearr_9746;
})();var statearr_9747_9783 = state_9743__$1;(statearr_9747_9783[(2)] = null);
(statearr_9747_9783[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (1)))
{var state_9743__$1 = state_9743;var statearr_9748_9784 = state_9743__$1;(statearr_9748_9784[(2)] = null);
(statearr_9748_9784[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (4)))
{var inst_9689 = (state_9743[(13)]);var inst_9689__$1 = (state_9743[(2)]);var inst_9690 = (inst_9689__$1 == null);var state_9743__$1 = (function (){var statearr_9752 = state_9743;(statearr_9752[(13)] = inst_9689__$1);
return statearr_9752;
})();if(cljs.core.truth_(inst_9690))
{var statearr_9753_9785 = state_9743__$1;(statearr_9753_9785[(1)] = (5));
} else
{var statearr_9754_9786 = state_9743__$1;(statearr_9754_9786[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (15)))
{var state_9743__$1 = state_9743;var statearr_9755_9787 = state_9743__$1;(statearr_9755_9787[(2)] = null);
(statearr_9755_9787[(1)] = (16));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (13)))
{var inst_9702 = (state_9743[(8)]);var inst_9700 = (state_9743[(9)]);var inst_9703 = (state_9743[(11)]);var inst_9701 = (state_9743[(12)]);var inst_9710 = (state_9743[(2)]);var inst_9711 = (inst_9703 + (1));var tmp9749 = inst_9702;var tmp9750 = inst_9700;var tmp9751 = inst_9701;var inst_9700__$1 = tmp9750;var inst_9701__$1 = tmp9751;var inst_9702__$1 = tmp9749;var inst_9703__$1 = inst_9711;var state_9743__$1 = (function (){var statearr_9756 = state_9743;(statearr_9756[(8)] = inst_9702__$1);
(statearr_9756[(14)] = inst_9710);
(statearr_9756[(9)] = inst_9700__$1);
(statearr_9756[(11)] = inst_9703__$1);
(statearr_9756[(12)] = inst_9701__$1);
return statearr_9756;
})();var statearr_9757_9788 = state_9743__$1;(statearr_9757_9788[(2)] = null);
(statearr_9757_9788[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (6)))
{var inst_9689 = (state_9743[(13)]);var inst_9694 = f.call(null,inst_9689);var inst_9699 = cljs.core.seq.call(null,inst_9694);var inst_9700 = inst_9699;var inst_9701 = null;var inst_9702 = (0);var inst_9703 = (0);var state_9743__$1 = (function (){var statearr_9758 = state_9743;(statearr_9758[(8)] = inst_9702);
(statearr_9758[(9)] = inst_9700);
(statearr_9758[(11)] = inst_9703);
(statearr_9758[(12)] = inst_9701);
return statearr_9758;
})();var statearr_9759_9789 = state_9743__$1;(statearr_9759_9789[(2)] = null);
(statearr_9759_9789[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (17)))
{var inst_9714 = (state_9743[(7)]);var inst_9718 = cljs.core.chunk_first.call(null,inst_9714);var inst_9719 = cljs.core.chunk_rest.call(null,inst_9714);var inst_9720 = cljs.core.count.call(null,inst_9718);var inst_9700 = inst_9719;var inst_9701 = inst_9718;var inst_9702 = inst_9720;var inst_9703 = (0);var state_9743__$1 = (function (){var statearr_9760 = state_9743;(statearr_9760[(8)] = inst_9702);
(statearr_9760[(9)] = inst_9700);
(statearr_9760[(11)] = inst_9703);
(statearr_9760[(12)] = inst_9701);
return statearr_9760;
})();var statearr_9761_9790 = state_9743__$1;(statearr_9761_9790[(2)] = null);
(statearr_9761_9790[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (3)))
{var inst_9741 = (state_9743[(2)]);var state_9743__$1 = state_9743;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_9743__$1,inst_9741);
} else
{if((state_val_9744 === (12)))
{var inst_9734 = (state_9743[(2)]);var state_9743__$1 = state_9743;var statearr_9762_9791 = state_9743__$1;(statearr_9762_9791[(2)] = inst_9734);
(statearr_9762_9791[(1)] = (9));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (2)))
{var state_9743__$1 = state_9743;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_9743__$1,(4),in$);
} else
{if((state_val_9744 === (19)))
{var inst_9729 = (state_9743[(2)]);var state_9743__$1 = state_9743;var statearr_9763_9792 = state_9743__$1;(statearr_9763_9792[(2)] = inst_9729);
(statearr_9763_9792[(1)] = (16));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (11)))
{var inst_9714 = (state_9743[(7)]);var inst_9700 = (state_9743[(9)]);var inst_9714__$1 = cljs.core.seq.call(null,inst_9700);var state_9743__$1 = (function (){var statearr_9764 = state_9743;(statearr_9764[(7)] = inst_9714__$1);
return statearr_9764;
})();if(inst_9714__$1)
{var statearr_9765_9793 = state_9743__$1;(statearr_9765_9793[(1)] = (14));
} else
{var statearr_9766_9794 = state_9743__$1;(statearr_9766_9794[(1)] = (15));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (9)))
{var inst_9736 = (state_9743[(2)]);var state_9743__$1 = (function (){var statearr_9767 = state_9743;(statearr_9767[(15)] = inst_9736);
return statearr_9767;
})();var statearr_9768_9795 = state_9743__$1;(statearr_9768_9795[(2)] = null);
(statearr_9768_9795[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (5)))
{var inst_9692 = cljs.core.async.close_BANG_.call(null,out);var state_9743__$1 = state_9743;var statearr_9769_9796 = state_9743__$1;(statearr_9769_9796[(2)] = inst_9692);
(statearr_9769_9796[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (14)))
{var inst_9714 = (state_9743[(7)]);var inst_9716 = cljs.core.chunked_seq_QMARK_.call(null,inst_9714);var state_9743__$1 = state_9743;if(inst_9716)
{var statearr_9770_9797 = state_9743__$1;(statearr_9770_9797[(1)] = (17));
} else
{var statearr_9771_9798 = state_9743__$1;(statearr_9771_9798[(1)] = (18));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (16)))
{var inst_9732 = (state_9743[(2)]);var state_9743__$1 = state_9743;var statearr_9772_9799 = state_9743__$1;(statearr_9772_9799[(2)] = inst_9732);
(statearr_9772_9799[(1)] = (12));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9744 === (10)))
{var inst_9703 = (state_9743[(11)]);var inst_9701 = (state_9743[(12)]);var inst_9708 = cljs.core._nth.call(null,inst_9701,inst_9703);var state_9743__$1 = state_9743;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_9743__$1,(13),out,inst_9708);
} else
{if((state_val_9744 === (18)))
{var inst_9714 = (state_9743[(7)]);var inst_9723 = cljs.core.first.call(null,inst_9714);var state_9743__$1 = state_9743;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_9743__$1,(20),out,inst_9723);
} else
{if((state_val_9744 === (8)))
{var inst_9702 = (state_9743[(8)]);var inst_9703 = (state_9743[(11)]);var inst_9705 = (inst_9703 < inst_9702);var inst_9706 = inst_9705;var state_9743__$1 = state_9743;if(cljs.core.truth_(inst_9706))
{var statearr_9773_9800 = state_9743__$1;(statearr_9773_9800[(1)] = (10));
} else
{var statearr_9774_9801 = state_9743__$1;(statearr_9774_9801[(1)] = (11));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto__))
;return ((function (switch__6338__auto__,c__6353__auto__){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_9778 = [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null];(statearr_9778[(0)] = state_machine__6339__auto__);
(statearr_9778[(1)] = (1));
return statearr_9778;
});
var state_machine__6339__auto____1 = (function (state_9743){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_9743);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e9779){if((e9779 instanceof Object))
{var ex__6342__auto__ = e9779;var statearr_9780_9802 = state_9743;(statearr_9780_9802[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_9743);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e9779;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__9803 = state_9743;
state_9743 = G__9803;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_9743){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_9743);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto__))
})();var state__6355__auto__ = (function (){var statearr_9781 = f__6354__auto__.call(null);(statearr_9781[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto__);
return statearr_9781;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto__))
);
return c__6353__auto__;
});
/**
* Takes a function and a source channel, and returns a channel which
* contains the values in each collection produced by applying f to
* each value taken from the source channel. f must return a
* collection.
* 
* The returned channel will be unbuffered by default, or a buf-or-n
* can be supplied. The channel will close when the source channel
* closes.
*/
cljs.core.async.mapcat_LT_ = (function() {
var mapcat_LT_ = null;
var mapcat_LT___2 = (function (f,in$){return mapcat_LT_.call(null,f,in$,null);
});
var mapcat_LT___3 = (function (f,in$,buf_or_n){var out = cljs.core.async.chan.call(null,buf_or_n);cljs.core.async.mapcat_STAR_.call(null,f,in$,out);
return out;
});
mapcat_LT_ = function(f,in$,buf_or_n){
switch(arguments.length){
case 2:
return mapcat_LT___2.call(this,f,in$);
case 3:
return mapcat_LT___3.call(this,f,in$,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
mapcat_LT_.cljs$core$IFn$_invoke$arity$2 = mapcat_LT___2;
mapcat_LT_.cljs$core$IFn$_invoke$arity$3 = mapcat_LT___3;
return mapcat_LT_;
})()
;
/**
* Takes a function and a target channel, and returns a channel which
* applies f to each value put, then supplies each element of the result
* to the target channel. f must return a collection.
* 
* The returned channel will be unbuffered by default, or a buf-or-n
* can be supplied. The target channel will be closed when the source
* channel closes.
*/
cljs.core.async.mapcat_GT_ = (function() {
var mapcat_GT_ = null;
var mapcat_GT___2 = (function (f,out){return mapcat_GT_.call(null,f,out,null);
});
var mapcat_GT___3 = (function (f,out,buf_or_n){var in$ = cljs.core.async.chan.call(null,buf_or_n);cljs.core.async.mapcat_STAR_.call(null,f,in$,out);
return in$;
});
mapcat_GT_ = function(f,out,buf_or_n){
switch(arguments.length){
case 2:
return mapcat_GT___2.call(this,f,out);
case 3:
return mapcat_GT___3.call(this,f,out,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
mapcat_GT_.cljs$core$IFn$_invoke$arity$2 = mapcat_GT___2;
mapcat_GT_.cljs$core$IFn$_invoke$arity$3 = mapcat_GT___3;
return mapcat_GT_;
})()
;
/**
* Takes elements from the from channel and supplies them to the to
* channel. By default, the to channel will be closed when the
* from channel closes, but can be determined by the close?
* parameter.
*/
cljs.core.async.pipe = (function() {
var pipe = null;
var pipe__2 = (function (from,to){return pipe.call(null,from,to,true);
});
var pipe__3 = (function (from,to,close_QMARK_){var c__6353__auto___9884 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___9884){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___9884){
return (function (state_9863){var state_val_9864 = (state_9863[(1)]);if((state_val_9864 === (7)))
{var inst_9859 = (state_9863[(2)]);var state_9863__$1 = state_9863;var statearr_9865_9885 = state_9863__$1;(statearr_9865_9885[(2)] = inst_9859);
(statearr_9865_9885[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9864 === (1)))
{var state_9863__$1 = state_9863;var statearr_9866_9886 = state_9863__$1;(statearr_9866_9886[(2)] = null);
(statearr_9866_9886[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9864 === (4)))
{var inst_9846 = (state_9863[(7)]);var inst_9846__$1 = (state_9863[(2)]);var inst_9847 = (inst_9846__$1 == null);var state_9863__$1 = (function (){var statearr_9867 = state_9863;(statearr_9867[(7)] = inst_9846__$1);
return statearr_9867;
})();if(cljs.core.truth_(inst_9847))
{var statearr_9868_9887 = state_9863__$1;(statearr_9868_9887[(1)] = (5));
} else
{var statearr_9869_9888 = state_9863__$1;(statearr_9869_9888[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9864 === (6)))
{var inst_9846 = (state_9863[(7)]);var state_9863__$1 = state_9863;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_9863__$1,(11),to,inst_9846);
} else
{if((state_val_9864 === (3)))
{var inst_9861 = (state_9863[(2)]);var state_9863__$1 = state_9863;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_9863__$1,inst_9861);
} else
{if((state_val_9864 === (2)))
{var state_9863__$1 = state_9863;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_9863__$1,(4),from);
} else
{if((state_val_9864 === (11)))
{var inst_9856 = (state_9863[(2)]);var state_9863__$1 = (function (){var statearr_9870 = state_9863;(statearr_9870[(8)] = inst_9856);
return statearr_9870;
})();var statearr_9871_9889 = state_9863__$1;(statearr_9871_9889[(2)] = null);
(statearr_9871_9889[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9864 === (9)))
{var state_9863__$1 = state_9863;var statearr_9872_9890 = state_9863__$1;(statearr_9872_9890[(2)] = null);
(statearr_9872_9890[(1)] = (10));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9864 === (5)))
{var state_9863__$1 = state_9863;if(cljs.core.truth_(close_QMARK_))
{var statearr_9873_9891 = state_9863__$1;(statearr_9873_9891[(1)] = (8));
} else
{var statearr_9874_9892 = state_9863__$1;(statearr_9874_9892[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9864 === (10)))
{var inst_9853 = (state_9863[(2)]);var state_9863__$1 = state_9863;var statearr_9875_9893 = state_9863__$1;(statearr_9875_9893[(2)] = inst_9853);
(statearr_9875_9893[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9864 === (8)))
{var inst_9850 = cljs.core.async.close_BANG_.call(null,to);var state_9863__$1 = state_9863;var statearr_9876_9894 = state_9863__$1;(statearr_9876_9894[(2)] = inst_9850);
(statearr_9876_9894[(1)] = (10));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___9884))
;return ((function (switch__6338__auto__,c__6353__auto___9884){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_9880 = [null,null,null,null,null,null,null,null,null];(statearr_9880[(0)] = state_machine__6339__auto__);
(statearr_9880[(1)] = (1));
return statearr_9880;
});
var state_machine__6339__auto____1 = (function (state_9863){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_9863);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e9881){if((e9881 instanceof Object))
{var ex__6342__auto__ = e9881;var statearr_9882_9895 = state_9863;(statearr_9882_9895[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_9863);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e9881;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__9896 = state_9863;
state_9863 = G__9896;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_9863){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_9863);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___9884))
})();var state__6355__auto__ = (function (){var statearr_9883 = f__6354__auto__.call(null);(statearr_9883[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___9884);
return statearr_9883;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___9884))
);
return to;
});
pipe = function(from,to,close_QMARK_){
switch(arguments.length){
case 2:
return pipe__2.call(this,from,to);
case 3:
return pipe__3.call(this,from,to,close_QMARK_);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
pipe.cljs$core$IFn$_invoke$arity$2 = pipe__2;
pipe.cljs$core$IFn$_invoke$arity$3 = pipe__3;
return pipe;
})()
;
/**
* Takes a predicate and a source channel and returns a vector of two
* channels, the first of which will contain the values for which the
* predicate returned true, the second those for which it returned
* false.
* 
* The out channels will be unbuffered by default, or two buf-or-ns can
* be supplied. The channels will close after the source channel has
* closed.
*/
cljs.core.async.split = (function() {
var split = null;
var split__2 = (function (p,ch){return split.call(null,p,ch,null,null);
});
var split__4 = (function (p,ch,t_buf_or_n,f_buf_or_n){var tc = cljs.core.async.chan.call(null,t_buf_or_n);var fc = cljs.core.async.chan.call(null,f_buf_or_n);var c__6353__auto___9983 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___9983,tc,fc){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___9983,tc,fc){
return (function (state_9961){var state_val_9962 = (state_9961[(1)]);if((state_val_9962 === (7)))
{var inst_9957 = (state_9961[(2)]);var state_9961__$1 = state_9961;var statearr_9963_9984 = state_9961__$1;(statearr_9963_9984[(2)] = inst_9957);
(statearr_9963_9984[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9962 === (1)))
{var state_9961__$1 = state_9961;var statearr_9964_9985 = state_9961__$1;(statearr_9964_9985[(2)] = null);
(statearr_9964_9985[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9962 === (4)))
{var inst_9942 = (state_9961[(7)]);var inst_9942__$1 = (state_9961[(2)]);var inst_9943 = (inst_9942__$1 == null);var state_9961__$1 = (function (){var statearr_9965 = state_9961;(statearr_9965[(7)] = inst_9942__$1);
return statearr_9965;
})();if(cljs.core.truth_(inst_9943))
{var statearr_9966_9986 = state_9961__$1;(statearr_9966_9986[(1)] = (5));
} else
{var statearr_9967_9987 = state_9961__$1;(statearr_9967_9987[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9962 === (6)))
{var inst_9942 = (state_9961[(7)]);var inst_9948 = p.call(null,inst_9942);var state_9961__$1 = state_9961;if(cljs.core.truth_(inst_9948))
{var statearr_9968_9988 = state_9961__$1;(statearr_9968_9988[(1)] = (9));
} else
{var statearr_9969_9989 = state_9961__$1;(statearr_9969_9989[(1)] = (10));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9962 === (3)))
{var inst_9959 = (state_9961[(2)]);var state_9961__$1 = state_9961;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_9961__$1,inst_9959);
} else
{if((state_val_9962 === (2)))
{var state_9961__$1 = state_9961;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_9961__$1,(4),ch);
} else
{if((state_val_9962 === (11)))
{var inst_9942 = (state_9961[(7)]);var inst_9952 = (state_9961[(2)]);var state_9961__$1 = state_9961;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_9961__$1,(8),inst_9952,inst_9942);
} else
{if((state_val_9962 === (9)))
{var state_9961__$1 = state_9961;var statearr_9970_9990 = state_9961__$1;(statearr_9970_9990[(2)] = tc);
(statearr_9970_9990[(1)] = (11));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9962 === (5)))
{var inst_9945 = cljs.core.async.close_BANG_.call(null,tc);var inst_9946 = cljs.core.async.close_BANG_.call(null,fc);var state_9961__$1 = (function (){var statearr_9971 = state_9961;(statearr_9971[(8)] = inst_9945);
return statearr_9971;
})();var statearr_9972_9991 = state_9961__$1;(statearr_9972_9991[(2)] = inst_9946);
(statearr_9972_9991[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9962 === (10)))
{var state_9961__$1 = state_9961;var statearr_9973_9992 = state_9961__$1;(statearr_9973_9992[(2)] = fc);
(statearr_9973_9992[(1)] = (11));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_9962 === (8)))
{var inst_9954 = (state_9961[(2)]);var state_9961__$1 = (function (){var statearr_9974 = state_9961;(statearr_9974[(9)] = inst_9954);
return statearr_9974;
})();var statearr_9975_9993 = state_9961__$1;(statearr_9975_9993[(2)] = null);
(statearr_9975_9993[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___9983,tc,fc))
;return ((function (switch__6338__auto__,c__6353__auto___9983,tc,fc){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_9979 = [null,null,null,null,null,null,null,null,null,null];(statearr_9979[(0)] = state_machine__6339__auto__);
(statearr_9979[(1)] = (1));
return statearr_9979;
});
var state_machine__6339__auto____1 = (function (state_9961){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_9961);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e9980){if((e9980 instanceof Object))
{var ex__6342__auto__ = e9980;var statearr_9981_9994 = state_9961;(statearr_9981_9994[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_9961);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e9980;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__9995 = state_9961;
state_9961 = G__9995;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_9961){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_9961);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___9983,tc,fc))
})();var state__6355__auto__ = (function (){var statearr_9982 = f__6354__auto__.call(null);(statearr_9982[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___9983);
return statearr_9982;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___9983,tc,fc))
);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [tc,fc], null);
});
split = function(p,ch,t_buf_or_n,f_buf_or_n){
switch(arguments.length){
case 2:
return split__2.call(this,p,ch);
case 4:
return split__4.call(this,p,ch,t_buf_or_n,f_buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
split.cljs$core$IFn$_invoke$arity$2 = split__2;
split.cljs$core$IFn$_invoke$arity$4 = split__4;
return split;
})()
;
/**
* f should be a function of 2 arguments. Returns a channel containing
* the single result of applying f to init and the first item from the
* channel, then applying f to that result and the 2nd item, etc. If
* the channel closes without yielding items, returns init and f is not
* called. ch must close before reduce produces a result.
*/
cljs.core.async.reduce = (function reduce(f,init,ch){var c__6353__auto__ = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto__){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto__){
return (function (state_10042){var state_val_10043 = (state_10042[(1)]);if((state_val_10043 === (7)))
{var inst_10038 = (state_10042[(2)]);var state_10042__$1 = state_10042;var statearr_10044_10060 = state_10042__$1;(statearr_10044_10060[(2)] = inst_10038);
(statearr_10044_10060[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10043 === (6)))
{var inst_10031 = (state_10042[(7)]);var inst_10028 = (state_10042[(8)]);var inst_10035 = f.call(null,inst_10028,inst_10031);var inst_10028__$1 = inst_10035;var state_10042__$1 = (function (){var statearr_10045 = state_10042;(statearr_10045[(8)] = inst_10028__$1);
return statearr_10045;
})();var statearr_10046_10061 = state_10042__$1;(statearr_10046_10061[(2)] = null);
(statearr_10046_10061[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10043 === (5)))
{var inst_10028 = (state_10042[(8)]);var state_10042__$1 = state_10042;var statearr_10047_10062 = state_10042__$1;(statearr_10047_10062[(2)] = inst_10028);
(statearr_10047_10062[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10043 === (4)))
{var inst_10031 = (state_10042[(7)]);var inst_10031__$1 = (state_10042[(2)]);var inst_10032 = (inst_10031__$1 == null);var state_10042__$1 = (function (){var statearr_10048 = state_10042;(statearr_10048[(7)] = inst_10031__$1);
return statearr_10048;
})();if(cljs.core.truth_(inst_10032))
{var statearr_10049_10063 = state_10042__$1;(statearr_10049_10063[(1)] = (5));
} else
{var statearr_10050_10064 = state_10042__$1;(statearr_10050_10064[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10043 === (3)))
{var inst_10040 = (state_10042[(2)]);var state_10042__$1 = state_10042;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_10042__$1,inst_10040);
} else
{if((state_val_10043 === (2)))
{var state_10042__$1 = state_10042;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_10042__$1,(4),ch);
} else
{if((state_val_10043 === (1)))
{var inst_10028 = init;var state_10042__$1 = (function (){var statearr_10051 = state_10042;(statearr_10051[(8)] = inst_10028);
return statearr_10051;
})();var statearr_10052_10065 = state_10042__$1;(statearr_10052_10065[(2)] = null);
(statearr_10052_10065[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
});})(c__6353__auto__))
;return ((function (switch__6338__auto__,c__6353__auto__){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_10056 = [null,null,null,null,null,null,null,null,null];(statearr_10056[(0)] = state_machine__6339__auto__);
(statearr_10056[(1)] = (1));
return statearr_10056;
});
var state_machine__6339__auto____1 = (function (state_10042){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_10042);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e10057){if((e10057 instanceof Object))
{var ex__6342__auto__ = e10057;var statearr_10058_10066 = state_10042;(statearr_10058_10066[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10042);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e10057;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__10067 = state_10042;
state_10042 = G__10067;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_10042){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_10042);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto__))
})();var state__6355__auto__ = (function (){var statearr_10059 = f__6354__auto__.call(null);(statearr_10059[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto__);
return statearr_10059;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto__))
);
return c__6353__auto__;
});
/**
* Puts the contents of coll into the supplied channel.
* 
* By default the channel will be closed after the items are copied,
* but can be determined by the close? parameter.
* 
* Returns a channel which will close after the items are copied.
*/
cljs.core.async.onto_chan = (function() {
var onto_chan = null;
var onto_chan__2 = (function (ch,coll){return onto_chan.call(null,ch,coll,true);
});
var onto_chan__3 = (function (ch,coll,close_QMARK_){var c__6353__auto__ = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto__){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto__){
return (function (state_10129){var state_val_10130 = (state_10129[(1)]);if((state_val_10130 === (7)))
{var inst_10110 = (state_10129[(7)]);var inst_10115 = (state_10129[(2)]);var inst_10116 = cljs.core.next.call(null,inst_10110);var inst_10110__$1 = inst_10116;var state_10129__$1 = (function (){var statearr_10131 = state_10129;(statearr_10131[(7)] = inst_10110__$1);
(statearr_10131[(8)] = inst_10115);
return statearr_10131;
})();var statearr_10132_10150 = state_10129__$1;(statearr_10132_10150[(2)] = null);
(statearr_10132_10150[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10130 === (1)))
{var inst_10109 = cljs.core.seq.call(null,coll);var inst_10110 = inst_10109;var state_10129__$1 = (function (){var statearr_10133 = state_10129;(statearr_10133[(7)] = inst_10110);
return statearr_10133;
})();var statearr_10134_10151 = state_10129__$1;(statearr_10134_10151[(2)] = null);
(statearr_10134_10151[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10130 === (4)))
{var inst_10110 = (state_10129[(7)]);var inst_10113 = cljs.core.first.call(null,inst_10110);var state_10129__$1 = state_10129;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_10129__$1,(7),ch,inst_10113);
} else
{if((state_val_10130 === (6)))
{var inst_10125 = (state_10129[(2)]);var state_10129__$1 = state_10129;var statearr_10135_10152 = state_10129__$1;(statearr_10135_10152[(2)] = inst_10125);
(statearr_10135_10152[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10130 === (3)))
{var inst_10127 = (state_10129[(2)]);var state_10129__$1 = state_10129;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_10129__$1,inst_10127);
} else
{if((state_val_10130 === (2)))
{var inst_10110 = (state_10129[(7)]);var state_10129__$1 = state_10129;if(cljs.core.truth_(inst_10110))
{var statearr_10136_10153 = state_10129__$1;(statearr_10136_10153[(1)] = (4));
} else
{var statearr_10137_10154 = state_10129__$1;(statearr_10137_10154[(1)] = (5));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10130 === (9)))
{var state_10129__$1 = state_10129;var statearr_10138_10155 = state_10129__$1;(statearr_10138_10155[(2)] = null);
(statearr_10138_10155[(1)] = (10));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10130 === (5)))
{var state_10129__$1 = state_10129;if(cljs.core.truth_(close_QMARK_))
{var statearr_10139_10156 = state_10129__$1;(statearr_10139_10156[(1)] = (8));
} else
{var statearr_10140_10157 = state_10129__$1;(statearr_10140_10157[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10130 === (10)))
{var inst_10123 = (state_10129[(2)]);var state_10129__$1 = state_10129;var statearr_10141_10158 = state_10129__$1;(statearr_10141_10158[(2)] = inst_10123);
(statearr_10141_10158[(1)] = (6));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10130 === (8)))
{var inst_10120 = cljs.core.async.close_BANG_.call(null,ch);var state_10129__$1 = state_10129;var statearr_10142_10159 = state_10129__$1;(statearr_10142_10159[(2)] = inst_10120);
(statearr_10142_10159[(1)] = (10));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto__))
;return ((function (switch__6338__auto__,c__6353__auto__){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_10146 = [null,null,null,null,null,null,null,null,null];(statearr_10146[(0)] = state_machine__6339__auto__);
(statearr_10146[(1)] = (1));
return statearr_10146;
});
var state_machine__6339__auto____1 = (function (state_10129){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_10129);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e10147){if((e10147 instanceof Object))
{var ex__6342__auto__ = e10147;var statearr_10148_10160 = state_10129;(statearr_10148_10160[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10129);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e10147;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__10161 = state_10129;
state_10129 = G__10161;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_10129){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_10129);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto__))
})();var state__6355__auto__ = (function (){var statearr_10149 = f__6354__auto__.call(null);(statearr_10149[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto__);
return statearr_10149;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto__))
);
return c__6353__auto__;
});
onto_chan = function(ch,coll,close_QMARK_){
switch(arguments.length){
case 2:
return onto_chan__2.call(this,ch,coll);
case 3:
return onto_chan__3.call(this,ch,coll,close_QMARK_);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
onto_chan.cljs$core$IFn$_invoke$arity$2 = onto_chan__2;
onto_chan.cljs$core$IFn$_invoke$arity$3 = onto_chan__3;
return onto_chan;
})()
;
/**
* Creates and returns a channel which contains the contents of coll,
* closing when exhausted.
*/
cljs.core.async.to_chan = (function to_chan(coll){var ch = cljs.core.async.chan.call(null,cljs.core.bounded_count.call(null,(100),coll));cljs.core.async.onto_chan.call(null,ch,coll);
return ch;
});
cljs.core.async.Mux = (function (){var obj10163 = {};return obj10163;
})();
cljs.core.async.muxch_STAR_ = (function muxch_STAR_(_){if((function (){var and__3539__auto__ = _;if(and__3539__auto__)
{return _.cljs$core$async$Mux$muxch_STAR_$arity$1;
} else
{return and__3539__auto__;
}
})())
{return _.cljs$core$async$Mux$muxch_STAR_$arity$1(_);
} else
{var x__4178__auto__ = (((_ == null))?null:_);return (function (){var or__3551__auto__ = (cljs.core.async.muxch_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.muxch_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mux.muxch*",_);
}
}
})().call(null,_);
}
});
cljs.core.async.Mult = (function (){var obj10165 = {};return obj10165;
})();
cljs.core.async.tap_STAR_ = (function tap_STAR_(m,ch,close_QMARK_){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mult$tap_STAR_$arity$3;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mult$tap_STAR_$arity$3(m,ch,close_QMARK_);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.tap_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.tap_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mult.tap*",m);
}
}
})().call(null,m,ch,close_QMARK_);
}
});
cljs.core.async.untap_STAR_ = (function untap_STAR_(m,ch){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mult$untap_STAR_$arity$2;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mult$untap_STAR_$arity$2(m,ch);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.untap_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.untap_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mult.untap*",m);
}
}
})().call(null,m,ch);
}
});
cljs.core.async.untap_all_STAR_ = (function untap_all_STAR_(m){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mult$untap_all_STAR_$arity$1;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mult$untap_all_STAR_$arity$1(m);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.untap_all_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.untap_all_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mult.untap-all*",m);
}
}
})().call(null,m);
}
});
/**
* Creates and returns a mult(iple) of the supplied channel. Channels
* containing copies of the channel can be created with 'tap', and
* detached with 'untap'.
* 
* Each item is distributed to all taps in parallel and synchronously,
* i.e. each tap must accept before the next item is distributed. Use
* buffering/windowing to prevent slow taps from holding up the mult.
* 
* Items received when there are no taps get dropped.
* 
* If a tap put throws an exception, it will be removed from the mult.
*/
cljs.core.async.mult = (function mult(ch){var cs = cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY);var m = (function (){if(typeof cljs.core.async.t10389 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t10389 = (function (cs,ch,mult,meta10390){
this.cs = cs;
this.ch = ch;
this.mult = mult;
this.meta10390 = meta10390;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t10389.cljs$lang$type = true;
cljs.core.async.t10389.cljs$lang$ctorStr = "cljs.core.async/t10389";
cljs.core.async.t10389.cljs$lang$ctorPrWriter = ((function (cs){
return (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t10389");
});})(cs))
;
cljs.core.async.t10389.prototype.cljs$core$async$Mult$ = true;
cljs.core.async.t10389.prototype.cljs$core$async$Mult$tap_STAR_$arity$3 = ((function (cs){
return (function (_,ch__$2,close_QMARK_){var self__ = this;
var ___$1 = this;cljs.core.swap_BANG_.call(null,self__.cs,cljs.core.assoc,ch__$2,close_QMARK_);
return null;
});})(cs))
;
cljs.core.async.t10389.prototype.cljs$core$async$Mult$untap_STAR_$arity$2 = ((function (cs){
return (function (_,ch__$2){var self__ = this;
var ___$1 = this;cljs.core.swap_BANG_.call(null,self__.cs,cljs.core.dissoc,ch__$2);
return null;
});})(cs))
;
cljs.core.async.t10389.prototype.cljs$core$async$Mult$untap_all_STAR_$arity$1 = ((function (cs){
return (function (_){var self__ = this;
var ___$1 = this;cljs.core.reset_BANG_.call(null,self__.cs,cljs.core.PersistentArrayMap.EMPTY);
return null;
});})(cs))
;
cljs.core.async.t10389.prototype.cljs$core$async$Mux$ = true;
cljs.core.async.t10389.prototype.cljs$core$async$Mux$muxch_STAR_$arity$1 = ((function (cs){
return (function (_){var self__ = this;
var ___$1 = this;return self__.ch;
});})(cs))
;
cljs.core.async.t10389.prototype.cljs$core$IMeta$_meta$arity$1 = ((function (cs){
return (function (_10391){var self__ = this;
var _10391__$1 = this;return self__.meta10390;
});})(cs))
;
cljs.core.async.t10389.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = ((function (cs){
return (function (_10391,meta10390__$1){var self__ = this;
var _10391__$1 = this;return (new cljs.core.async.t10389(self__.cs,self__.ch,self__.mult,meta10390__$1));
});})(cs))
;
cljs.core.async.__GT_t10389 = ((function (cs){
return (function __GT_t10389(cs__$1,ch__$1,mult__$1,meta10390){return (new cljs.core.async.t10389(cs__$1,ch__$1,mult__$1,meta10390));
});})(cs))
;
}
return (new cljs.core.async.t10389(cs,ch,mult,null));
})();var dchan = cljs.core.async.chan.call(null,(1));var dctr = cljs.core.atom.call(null,null);var done = ((function (cs,m,dchan,dctr){
return (function (){if((cljs.core.swap_BANG_.call(null,dctr,cljs.core.dec) === (0)))
{return cljs.core.async.put_BANG_.call(null,dchan,true);
} else
{return null;
}
});})(cs,m,dchan,dctr))
;var c__6353__auto___10612 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___10612,cs,m,dchan,dctr,done){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___10612,cs,m,dchan,dctr,done){
return (function (state_10526){var state_val_10527 = (state_10526[(1)]);if((state_val_10527 === (7)))
{var inst_10522 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10528_10613 = state_10526__$1;(statearr_10528_10613[(2)] = inst_10522);
(statearr_10528_10613[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (20)))
{var inst_10423 = (state_10526[(7)]);var inst_10433 = cljs.core.first.call(null,inst_10423);var inst_10434 = cljs.core.nth.call(null,inst_10433,(0),null);var inst_10435 = cljs.core.nth.call(null,inst_10433,(1),null);var state_10526__$1 = (function (){var statearr_10529 = state_10526;(statearr_10529[(8)] = inst_10434);
return statearr_10529;
})();if(cljs.core.truth_(inst_10435))
{var statearr_10530_10614 = state_10526__$1;(statearr_10530_10614[(1)] = (22));
} else
{var statearr_10531_10615 = state_10526__$1;(statearr_10531_10615[(1)] = (23));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (27)))
{var inst_10463 = (state_10526[(9)]);var inst_10465 = (state_10526[(10)]);var inst_10470 = cljs.core._nth.call(null,inst_10463,inst_10465);var state_10526__$1 = (function (){var statearr_10532 = state_10526;(statearr_10532[(11)] = inst_10470);
return statearr_10532;
})();var statearr_10533_10616 = state_10526__$1;(statearr_10533_10616[(2)] = null);
(statearr_10533_10616[(1)] = (32));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (1)))
{var state_10526__$1 = state_10526;var statearr_10534_10617 = state_10526__$1;(statearr_10534_10617[(2)] = null);
(statearr_10534_10617[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (24)))
{var inst_10423 = (state_10526[(7)]);var inst_10440 = (state_10526[(2)]);var inst_10441 = cljs.core.next.call(null,inst_10423);var inst_10403 = inst_10441;var inst_10404 = null;var inst_10405 = (0);var inst_10406 = (0);var state_10526__$1 = (function (){var statearr_10535 = state_10526;(statearr_10535[(12)] = inst_10406);
(statearr_10535[(13)] = inst_10404);
(statearr_10535[(14)] = inst_10405);
(statearr_10535[(15)] = inst_10440);
(statearr_10535[(16)] = inst_10403);
return statearr_10535;
})();var statearr_10536_10618 = state_10526__$1;(statearr_10536_10618[(2)] = null);
(statearr_10536_10618[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (39)))
{var inst_10483 = (state_10526[(17)]);var inst_10501 = (state_10526[(2)]);var inst_10502 = cljs.core.next.call(null,inst_10483);var inst_10462 = inst_10502;var inst_10463 = null;var inst_10464 = (0);var inst_10465 = (0);var state_10526__$1 = (function (){var statearr_10540 = state_10526;(statearr_10540[(18)] = inst_10462);
(statearr_10540[(19)] = inst_10464);
(statearr_10540[(9)] = inst_10463);
(statearr_10540[(10)] = inst_10465);
(statearr_10540[(20)] = inst_10501);
return statearr_10540;
})();var statearr_10541_10619 = state_10526__$1;(statearr_10541_10619[(2)] = null);
(statearr_10541_10619[(1)] = (25));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (4)))
{var inst_10394 = (state_10526[(21)]);var inst_10394__$1 = (state_10526[(2)]);var inst_10395 = (inst_10394__$1 == null);var state_10526__$1 = (function (){var statearr_10542 = state_10526;(statearr_10542[(21)] = inst_10394__$1);
return statearr_10542;
})();if(cljs.core.truth_(inst_10395))
{var statearr_10543_10620 = state_10526__$1;(statearr_10543_10620[(1)] = (5));
} else
{var statearr_10544_10621 = state_10526__$1;(statearr_10544_10621[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (15)))
{var inst_10406 = (state_10526[(12)]);var inst_10404 = (state_10526[(13)]);var inst_10405 = (state_10526[(14)]);var inst_10403 = (state_10526[(16)]);var inst_10419 = (state_10526[(2)]);var inst_10420 = (inst_10406 + (1));var tmp10537 = inst_10404;var tmp10538 = inst_10405;var tmp10539 = inst_10403;var inst_10403__$1 = tmp10539;var inst_10404__$1 = tmp10537;var inst_10405__$1 = tmp10538;var inst_10406__$1 = inst_10420;var state_10526__$1 = (function (){var statearr_10545 = state_10526;(statearr_10545[(12)] = inst_10406__$1);
(statearr_10545[(13)] = inst_10404__$1);
(statearr_10545[(14)] = inst_10405__$1);
(statearr_10545[(16)] = inst_10403__$1);
(statearr_10545[(22)] = inst_10419);
return statearr_10545;
})();var statearr_10546_10622 = state_10526__$1;(statearr_10546_10622[(2)] = null);
(statearr_10546_10622[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (21)))
{var inst_10444 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10547_10623 = state_10526__$1;(statearr_10547_10623[(2)] = inst_10444);
(statearr_10547_10623[(1)] = (18));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (31)))
{var inst_10470 = (state_10526[(11)]);var inst_10471 = (state_10526[(2)]);var inst_10472 = cljs.core.swap_BANG_.call(null,dctr,cljs.core.dec);var inst_10473 = cljs.core.async.untap_STAR_.call(null,m,inst_10470);var state_10526__$1 = (function (){var statearr_10548 = state_10526;(statearr_10548[(23)] = inst_10471);
(statearr_10548[(24)] = inst_10472);
return statearr_10548;
})();var statearr_10549_10624 = state_10526__$1;(statearr_10549_10624[(2)] = inst_10473);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10526__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (32)))
{var inst_10470 = (state_10526[(11)]);var inst_10394 = (state_10526[(21)]);var _ = cljs.core.async.impl.ioc_helpers.add_exception_frame.call(null,state_10526,(31),Object,null,(30));var inst_10477 = cljs.core.async.put_BANG_.call(null,inst_10470,inst_10394,done);var state_10526__$1 = state_10526;var statearr_10550_10625 = state_10526__$1;(statearr_10550_10625[(2)] = inst_10477);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10526__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (40)))
{var inst_10492 = (state_10526[(25)]);var inst_10493 = (state_10526[(2)]);var inst_10494 = cljs.core.swap_BANG_.call(null,dctr,cljs.core.dec);var inst_10495 = cljs.core.async.untap_STAR_.call(null,m,inst_10492);var state_10526__$1 = (function (){var statearr_10551 = state_10526;(statearr_10551[(26)] = inst_10494);
(statearr_10551[(27)] = inst_10493);
return statearr_10551;
})();var statearr_10552_10626 = state_10526__$1;(statearr_10552_10626[(2)] = inst_10495);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10526__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (33)))
{var inst_10483 = (state_10526[(17)]);var inst_10485 = cljs.core.chunked_seq_QMARK_.call(null,inst_10483);var state_10526__$1 = state_10526;if(inst_10485)
{var statearr_10553_10627 = state_10526__$1;(statearr_10553_10627[(1)] = (36));
} else
{var statearr_10554_10628 = state_10526__$1;(statearr_10554_10628[(1)] = (37));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (13)))
{var inst_10413 = (state_10526[(28)]);var inst_10416 = cljs.core.async.close_BANG_.call(null,inst_10413);var state_10526__$1 = state_10526;var statearr_10555_10629 = state_10526__$1;(statearr_10555_10629[(2)] = inst_10416);
(statearr_10555_10629[(1)] = (15));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (22)))
{var inst_10434 = (state_10526[(8)]);var inst_10437 = cljs.core.async.close_BANG_.call(null,inst_10434);var state_10526__$1 = state_10526;var statearr_10556_10630 = state_10526__$1;(statearr_10556_10630[(2)] = inst_10437);
(statearr_10556_10630[(1)] = (24));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (36)))
{var inst_10483 = (state_10526[(17)]);var inst_10487 = cljs.core.chunk_first.call(null,inst_10483);var inst_10488 = cljs.core.chunk_rest.call(null,inst_10483);var inst_10489 = cljs.core.count.call(null,inst_10487);var inst_10462 = inst_10488;var inst_10463 = inst_10487;var inst_10464 = inst_10489;var inst_10465 = (0);var state_10526__$1 = (function (){var statearr_10557 = state_10526;(statearr_10557[(18)] = inst_10462);
(statearr_10557[(19)] = inst_10464);
(statearr_10557[(9)] = inst_10463);
(statearr_10557[(10)] = inst_10465);
return statearr_10557;
})();var statearr_10558_10631 = state_10526__$1;(statearr_10558_10631[(2)] = null);
(statearr_10558_10631[(1)] = (25));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (41)))
{var inst_10492 = (state_10526[(25)]);var inst_10394 = (state_10526[(21)]);var _ = cljs.core.async.impl.ioc_helpers.add_exception_frame.call(null,state_10526,(40),Object,null,(39));var inst_10499 = cljs.core.async.put_BANG_.call(null,inst_10492,inst_10394,done);var state_10526__$1 = state_10526;var statearr_10559_10632 = state_10526__$1;(statearr_10559_10632[(2)] = inst_10499);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10526__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (43)))
{var state_10526__$1 = state_10526;var statearr_10560_10633 = state_10526__$1;(statearr_10560_10633[(2)] = null);
(statearr_10560_10633[(1)] = (44));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (29)))
{var inst_10510 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10561_10634 = state_10526__$1;(statearr_10561_10634[(2)] = inst_10510);
(statearr_10561_10634[(1)] = (26));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (44)))
{var inst_10519 = (state_10526[(2)]);var state_10526__$1 = (function (){var statearr_10562 = state_10526;(statearr_10562[(29)] = inst_10519);
return statearr_10562;
})();var statearr_10563_10635 = state_10526__$1;(statearr_10563_10635[(2)] = null);
(statearr_10563_10635[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (6)))
{var inst_10454 = (state_10526[(30)]);var inst_10453 = cljs.core.deref.call(null,cs);var inst_10454__$1 = cljs.core.keys.call(null,inst_10453);var inst_10455 = cljs.core.count.call(null,inst_10454__$1);var inst_10456 = cljs.core.reset_BANG_.call(null,dctr,inst_10455);var inst_10461 = cljs.core.seq.call(null,inst_10454__$1);var inst_10462 = inst_10461;var inst_10463 = null;var inst_10464 = (0);var inst_10465 = (0);var state_10526__$1 = (function (){var statearr_10564 = state_10526;(statearr_10564[(18)] = inst_10462);
(statearr_10564[(19)] = inst_10464);
(statearr_10564[(31)] = inst_10456);
(statearr_10564[(30)] = inst_10454__$1);
(statearr_10564[(9)] = inst_10463);
(statearr_10564[(10)] = inst_10465);
return statearr_10564;
})();var statearr_10565_10636 = state_10526__$1;(statearr_10565_10636[(2)] = null);
(statearr_10565_10636[(1)] = (25));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (28)))
{var inst_10462 = (state_10526[(18)]);var inst_10483 = (state_10526[(17)]);var inst_10483__$1 = cljs.core.seq.call(null,inst_10462);var state_10526__$1 = (function (){var statearr_10566 = state_10526;(statearr_10566[(17)] = inst_10483__$1);
return statearr_10566;
})();if(inst_10483__$1)
{var statearr_10567_10637 = state_10526__$1;(statearr_10567_10637[(1)] = (33));
} else
{var statearr_10568_10638 = state_10526__$1;(statearr_10568_10638[(1)] = (34));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (25)))
{var inst_10464 = (state_10526[(19)]);var inst_10465 = (state_10526[(10)]);var inst_10467 = (inst_10465 < inst_10464);var inst_10468 = inst_10467;var state_10526__$1 = state_10526;if(cljs.core.truth_(inst_10468))
{var statearr_10569_10639 = state_10526__$1;(statearr_10569_10639[(1)] = (27));
} else
{var statearr_10570_10640 = state_10526__$1;(statearr_10570_10640[(1)] = (28));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (34)))
{var state_10526__$1 = state_10526;var statearr_10571_10641 = state_10526__$1;(statearr_10571_10641[(2)] = null);
(statearr_10571_10641[(1)] = (35));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (17)))
{var state_10526__$1 = state_10526;var statearr_10572_10642 = state_10526__$1;(statearr_10572_10642[(2)] = null);
(statearr_10572_10642[(1)] = (18));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (3)))
{var inst_10524 = (state_10526[(2)]);var state_10526__$1 = state_10526;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_10526__$1,inst_10524);
} else
{if((state_val_10527 === (12)))
{var inst_10449 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10573_10643 = state_10526__$1;(statearr_10573_10643[(2)] = inst_10449);
(statearr_10573_10643[(1)] = (9));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (2)))
{var state_10526__$1 = state_10526;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_10526__$1,(4),ch);
} else
{if((state_val_10527 === (23)))
{var state_10526__$1 = state_10526;var statearr_10574_10644 = state_10526__$1;(statearr_10574_10644[(2)] = null);
(statearr_10574_10644[(1)] = (24));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (35)))
{var inst_10508 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10575_10645 = state_10526__$1;(statearr_10575_10645[(2)] = inst_10508);
(statearr_10575_10645[(1)] = (29));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (19)))
{var inst_10423 = (state_10526[(7)]);var inst_10427 = cljs.core.chunk_first.call(null,inst_10423);var inst_10428 = cljs.core.chunk_rest.call(null,inst_10423);var inst_10429 = cljs.core.count.call(null,inst_10427);var inst_10403 = inst_10428;var inst_10404 = inst_10427;var inst_10405 = inst_10429;var inst_10406 = (0);var state_10526__$1 = (function (){var statearr_10576 = state_10526;(statearr_10576[(12)] = inst_10406);
(statearr_10576[(13)] = inst_10404);
(statearr_10576[(14)] = inst_10405);
(statearr_10576[(16)] = inst_10403);
return statearr_10576;
})();var statearr_10577_10646 = state_10526__$1;(statearr_10577_10646[(2)] = null);
(statearr_10577_10646[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (11)))
{var inst_10403 = (state_10526[(16)]);var inst_10423 = (state_10526[(7)]);var inst_10423__$1 = cljs.core.seq.call(null,inst_10403);var state_10526__$1 = (function (){var statearr_10578 = state_10526;(statearr_10578[(7)] = inst_10423__$1);
return statearr_10578;
})();if(inst_10423__$1)
{var statearr_10579_10647 = state_10526__$1;(statearr_10579_10647[(1)] = (16));
} else
{var statearr_10580_10648 = state_10526__$1;(statearr_10580_10648[(1)] = (17));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (9)))
{var inst_10451 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10581_10649 = state_10526__$1;(statearr_10581_10649[(2)] = inst_10451);
(statearr_10581_10649[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (5)))
{var inst_10401 = cljs.core.deref.call(null,cs);var inst_10402 = cljs.core.seq.call(null,inst_10401);var inst_10403 = inst_10402;var inst_10404 = null;var inst_10405 = (0);var inst_10406 = (0);var state_10526__$1 = (function (){var statearr_10582 = state_10526;(statearr_10582[(12)] = inst_10406);
(statearr_10582[(13)] = inst_10404);
(statearr_10582[(14)] = inst_10405);
(statearr_10582[(16)] = inst_10403);
return statearr_10582;
})();var statearr_10583_10650 = state_10526__$1;(statearr_10583_10650[(2)] = null);
(statearr_10583_10650[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (14)))
{var state_10526__$1 = state_10526;var statearr_10584_10651 = state_10526__$1;(statearr_10584_10651[(2)] = null);
(statearr_10584_10651[(1)] = (15));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (45)))
{var inst_10516 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10585_10652 = state_10526__$1;(statearr_10585_10652[(2)] = inst_10516);
(statearr_10585_10652[(1)] = (44));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (26)))
{var inst_10454 = (state_10526[(30)]);var inst_10512 = (state_10526[(2)]);var inst_10513 = cljs.core.seq.call(null,inst_10454);var state_10526__$1 = (function (){var statearr_10586 = state_10526;(statearr_10586[(32)] = inst_10512);
return statearr_10586;
})();if(inst_10513)
{var statearr_10587_10653 = state_10526__$1;(statearr_10587_10653[(1)] = (42));
} else
{var statearr_10588_10654 = state_10526__$1;(statearr_10588_10654[(1)] = (43));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (16)))
{var inst_10423 = (state_10526[(7)]);var inst_10425 = cljs.core.chunked_seq_QMARK_.call(null,inst_10423);var state_10526__$1 = state_10526;if(inst_10425)
{var statearr_10592_10655 = state_10526__$1;(statearr_10592_10655[(1)] = (19));
} else
{var statearr_10593_10656 = state_10526__$1;(statearr_10593_10656[(1)] = (20));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (38)))
{var inst_10505 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10594_10657 = state_10526__$1;(statearr_10594_10657[(2)] = inst_10505);
(statearr_10594_10657[(1)] = (35));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (30)))
{var inst_10462 = (state_10526[(18)]);var inst_10464 = (state_10526[(19)]);var inst_10463 = (state_10526[(9)]);var inst_10465 = (state_10526[(10)]);var inst_10479 = (state_10526[(2)]);var inst_10480 = (inst_10465 + (1));var tmp10589 = inst_10462;var tmp10590 = inst_10464;var tmp10591 = inst_10463;var inst_10462__$1 = tmp10589;var inst_10463__$1 = tmp10591;var inst_10464__$1 = tmp10590;var inst_10465__$1 = inst_10480;var state_10526__$1 = (function (){var statearr_10595 = state_10526;(statearr_10595[(18)] = inst_10462__$1);
(statearr_10595[(19)] = inst_10464__$1);
(statearr_10595[(9)] = inst_10463__$1);
(statearr_10595[(33)] = inst_10479);
(statearr_10595[(10)] = inst_10465__$1);
return statearr_10595;
})();var statearr_10596_10658 = state_10526__$1;(statearr_10596_10658[(2)] = null);
(statearr_10596_10658[(1)] = (25));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (10)))
{var inst_10406 = (state_10526[(12)]);var inst_10404 = (state_10526[(13)]);var inst_10412 = cljs.core._nth.call(null,inst_10404,inst_10406);var inst_10413 = cljs.core.nth.call(null,inst_10412,(0),null);var inst_10414 = cljs.core.nth.call(null,inst_10412,(1),null);var state_10526__$1 = (function (){var statearr_10597 = state_10526;(statearr_10597[(28)] = inst_10413);
return statearr_10597;
})();if(cljs.core.truth_(inst_10414))
{var statearr_10598_10659 = state_10526__$1;(statearr_10598_10659[(1)] = (13));
} else
{var statearr_10599_10660 = state_10526__$1;(statearr_10599_10660[(1)] = (14));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (18)))
{var inst_10447 = (state_10526[(2)]);var state_10526__$1 = state_10526;var statearr_10600_10661 = state_10526__$1;(statearr_10600_10661[(2)] = inst_10447);
(statearr_10600_10661[(1)] = (12));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (42)))
{var state_10526__$1 = state_10526;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_10526__$1,(45),dchan);
} else
{if((state_val_10527 === (37)))
{var inst_10483 = (state_10526[(17)]);var inst_10492 = cljs.core.first.call(null,inst_10483);var state_10526__$1 = (function (){var statearr_10601 = state_10526;(statearr_10601[(25)] = inst_10492);
return statearr_10601;
})();var statearr_10602_10662 = state_10526__$1;(statearr_10602_10662[(2)] = null);
(statearr_10602_10662[(1)] = (41));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10527 === (8)))
{var inst_10406 = (state_10526[(12)]);var inst_10405 = (state_10526[(14)]);var inst_10408 = (inst_10406 < inst_10405);var inst_10409 = inst_10408;var state_10526__$1 = state_10526;if(cljs.core.truth_(inst_10409))
{var statearr_10603_10663 = state_10526__$1;(statearr_10603_10663[(1)] = (10));
} else
{var statearr_10604_10664 = state_10526__$1;(statearr_10604_10664[(1)] = (11));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___10612,cs,m,dchan,dctr,done))
;return ((function (switch__6338__auto__,c__6353__auto___10612,cs,m,dchan,dctr,done){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_10608 = [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null];(statearr_10608[(0)] = state_machine__6339__auto__);
(statearr_10608[(1)] = (1));
return statearr_10608;
});
var state_machine__6339__auto____1 = (function (state_10526){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_10526);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e10609){if((e10609 instanceof Object))
{var ex__6342__auto__ = e10609;var statearr_10610_10665 = state_10526;(statearr_10610_10665[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10526);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e10609;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__10666 = state_10526;
state_10526 = G__10666;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_10526){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_10526);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___10612,cs,m,dchan,dctr,done))
})();var state__6355__auto__ = (function (){var statearr_10611 = f__6354__auto__.call(null);(statearr_10611[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___10612);
return statearr_10611;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___10612,cs,m,dchan,dctr,done))
);
return m;
});
/**
* Copies the mult source onto the supplied channel.
* 
* By default the channel will be closed when the source closes,
* but can be determined by the close? parameter.
*/
cljs.core.async.tap = (function() {
var tap = null;
var tap__2 = (function (mult,ch){return tap.call(null,mult,ch,true);
});
var tap__3 = (function (mult,ch,close_QMARK_){cljs.core.async.tap_STAR_.call(null,mult,ch,close_QMARK_);
return ch;
});
tap = function(mult,ch,close_QMARK_){
switch(arguments.length){
case 2:
return tap__2.call(this,mult,ch);
case 3:
return tap__3.call(this,mult,ch,close_QMARK_);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
tap.cljs$core$IFn$_invoke$arity$2 = tap__2;
tap.cljs$core$IFn$_invoke$arity$3 = tap__3;
return tap;
})()
;
/**
* Disconnects a target channel from a mult
*/
cljs.core.async.untap = (function untap(mult,ch){return cljs.core.async.untap_STAR_.call(null,mult,ch);
});
/**
* Disconnects all target channels from a mult
*/
cljs.core.async.untap_all = (function untap_all(mult){return cljs.core.async.untap_all_STAR_.call(null,mult);
});
cljs.core.async.Mix = (function (){var obj10668 = {};return obj10668;
})();
cljs.core.async.admix_STAR_ = (function admix_STAR_(m,ch){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mix$admix_STAR_$arity$2;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mix$admix_STAR_$arity$2(m,ch);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.admix_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.admix_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mix.admix*",m);
}
}
})().call(null,m,ch);
}
});
cljs.core.async.unmix_STAR_ = (function unmix_STAR_(m,ch){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mix$unmix_STAR_$arity$2;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mix$unmix_STAR_$arity$2(m,ch);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.unmix_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.unmix_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mix.unmix*",m);
}
}
})().call(null,m,ch);
}
});
cljs.core.async.unmix_all_STAR_ = (function unmix_all_STAR_(m){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mix$unmix_all_STAR_$arity$1;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mix$unmix_all_STAR_$arity$1(m);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.unmix_all_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.unmix_all_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mix.unmix-all*",m);
}
}
})().call(null,m);
}
});
cljs.core.async.toggle_STAR_ = (function toggle_STAR_(m,state_map){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mix$toggle_STAR_$arity$2;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mix$toggle_STAR_$arity$2(m,state_map);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.toggle_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.toggle_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mix.toggle*",m);
}
}
})().call(null,m,state_map);
}
});
cljs.core.async.solo_mode_STAR_ = (function solo_mode_STAR_(m,mode){if((function (){var and__3539__auto__ = m;if(and__3539__auto__)
{return m.cljs$core$async$Mix$solo_mode_STAR_$arity$2;
} else
{return and__3539__auto__;
}
})())
{return m.cljs$core$async$Mix$solo_mode_STAR_$arity$2(m,mode);
} else
{var x__4178__auto__ = (((m == null))?null:m);return (function (){var or__3551__auto__ = (cljs.core.async.solo_mode_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.solo_mode_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Mix.solo-mode*",m);
}
}
})().call(null,m,mode);
}
});
/**
* Creates and returns a mix of one or more input channels which will
* be put on the supplied out channel. Input sources can be added to
* the mix with 'admix', and removed with 'unmix'. A mix supports
* soloing, muting and pausing multiple inputs atomically using
* 'toggle', and can solo using either muting or pausing as determined
* by 'solo-mode'.
* 
* Each channel can have zero or more boolean modes set via 'toggle':
* 
* :solo - when true, only this (ond other soloed) channel(s) will appear
* in the mix output channel. :mute and :pause states of soloed
* channels are ignored. If solo-mode is :mute, non-soloed
* channels are muted, if :pause, non-soloed channels are
* paused.
* 
* :mute - muted channels will have their contents consumed but not included in the mix
* :pause - paused channels will not have their contents consumed (and thus also not included in the mix)
*/
cljs.core.async.mix = (function mix(out){var cs = cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY);var solo_modes = new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"pause","pause",-2095325672),null,new cljs.core.Keyword(null,"mute","mute",1151223646),null], null), null);var attrs = cljs.core.conj.call(null,solo_modes,new cljs.core.Keyword(null,"solo","solo",-316350075));var solo_mode = cljs.core.atom.call(null,new cljs.core.Keyword(null,"mute","mute",1151223646));var change = cljs.core.async.chan.call(null);var changed = ((function (cs,solo_modes,attrs,solo_mode,change){
return (function (){return cljs.core.async.put_BANG_.call(null,change,true);
});})(cs,solo_modes,attrs,solo_mode,change))
;var pick = ((function (cs,solo_modes,attrs,solo_mode,change,changed){
return (function (attr,chs){return cljs.core.reduce_kv.call(null,((function (cs,solo_modes,attrs,solo_mode,change,changed){
return (function (ret,c,v){if(cljs.core.truth_(attr.call(null,v)))
{return cljs.core.conj.call(null,ret,c);
} else
{return ret;
}
});})(cs,solo_modes,attrs,solo_mode,change,changed))
,cljs.core.PersistentHashSet.EMPTY,chs);
});})(cs,solo_modes,attrs,solo_mode,change,changed))
;var calc_state = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick){
return (function (){var chs = cljs.core.deref.call(null,cs);var mode = cljs.core.deref.call(null,solo_mode);var solos = pick.call(null,new cljs.core.Keyword(null,"solo","solo",-316350075),chs);var pauses = pick.call(null,new cljs.core.Keyword(null,"pause","pause",-2095325672),chs);return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"solos","solos",1441458643),solos,new cljs.core.Keyword(null,"mutes","mutes",1068806309),pick.call(null,new cljs.core.Keyword(null,"mute","mute",1151223646),chs),new cljs.core.Keyword(null,"reads","reads",-1215067361),cljs.core.conj.call(null,(((cljs.core._EQ_.call(null,mode,new cljs.core.Keyword(null,"pause","pause",-2095325672))) && (!(cljs.core.empty_QMARK_.call(null,solos))))?cljs.core.vec.call(null,solos):cljs.core.vec.call(null,cljs.core.remove.call(null,pauses,cljs.core.keys.call(null,chs)))),change)], null);
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick))
;var m = (function (){if(typeof cljs.core.async.t10778 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t10778 = (function (change,mix,solo_mode,pick,cs,calc_state,out,changed,solo_modes,attrs,meta10779){
this.change = change;
this.mix = mix;
this.solo_mode = solo_mode;
this.pick = pick;
this.cs = cs;
this.calc_state = calc_state;
this.out = out;
this.changed = changed;
this.solo_modes = solo_modes;
this.attrs = attrs;
this.meta10779 = meta10779;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t10778.cljs$lang$type = true;
cljs.core.async.t10778.cljs$lang$ctorStr = "cljs.core.async/t10778";
cljs.core.async.t10778.cljs$lang$ctorPrWriter = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t10778");
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$async$Mix$ = true;
cljs.core.async.t10778.prototype.cljs$core$async$Mix$admix_STAR_$arity$2 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_,ch){var self__ = this;
var ___$1 = this;cljs.core.swap_BANG_.call(null,self__.cs,cljs.core.assoc,ch,cljs.core.PersistentArrayMap.EMPTY);
return self__.changed.call(null);
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$async$Mix$unmix_STAR_$arity$2 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_,ch){var self__ = this;
var ___$1 = this;cljs.core.swap_BANG_.call(null,self__.cs,cljs.core.dissoc,ch);
return self__.changed.call(null);
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$async$Mix$unmix_all_STAR_$arity$1 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_){var self__ = this;
var ___$1 = this;cljs.core.reset_BANG_.call(null,self__.cs,cljs.core.PersistentArrayMap.EMPTY);
return self__.changed.call(null);
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$async$Mix$toggle_STAR_$arity$2 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_,state_map){var self__ = this;
var ___$1 = this;cljs.core.swap_BANG_.call(null,self__.cs,cljs.core.partial.call(null,cljs.core.merge_with,cljs.core.merge),state_map);
return self__.changed.call(null);
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$async$Mix$solo_mode_STAR_$arity$2 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_,mode){var self__ = this;
var ___$1 = this;if(cljs.core.truth_(self__.solo_modes.call(null,mode)))
{} else
{throw (new Error(("Assert failed: "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(("mode must be one of: "+cljs.core.str.cljs$core$IFn$_invoke$arity$1(self__.solo_modes)))+"\n"+cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.pr_str.call(null,cljs.core.list(new cljs.core.Symbol(null,"solo-modes","solo-modes",882180540,null),new cljs.core.Symbol(null,"mode","mode",-2000032078,null)))))));
}
cljs.core.reset_BANG_.call(null,self__.solo_mode,mode);
return self__.changed.call(null);
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$async$Mux$ = true;
cljs.core.async.t10778.prototype.cljs$core$async$Mux$muxch_STAR_$arity$1 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_){var self__ = this;
var ___$1 = this;return self__.out;
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$IMeta$_meta$arity$1 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_10780){var self__ = this;
var _10780__$1 = this;return self__.meta10779;
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.t10778.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function (_10780,meta10779__$1){var self__ = this;
var _10780__$1 = this;return (new cljs.core.async.t10778(self__.change,self__.mix,self__.solo_mode,self__.pick,self__.cs,self__.calc_state,self__.out,self__.changed,self__.solo_modes,self__.attrs,meta10779__$1));
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
cljs.core.async.__GT_t10778 = ((function (cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state){
return (function __GT_t10778(change__$1,mix__$1,solo_mode__$1,pick__$1,cs__$1,calc_state__$1,out__$1,changed__$1,solo_modes__$1,attrs__$1,meta10779){return (new cljs.core.async.t10778(change__$1,mix__$1,solo_mode__$1,pick__$1,cs__$1,calc_state__$1,out__$1,changed__$1,solo_modes__$1,attrs__$1,meta10779));
});})(cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state))
;
}
return (new cljs.core.async.t10778(change,mix,solo_mode,pick,cs,calc_state,out,changed,solo_modes,attrs,null));
})();var c__6353__auto___10887 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___10887,cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state,m){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___10887,cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state,m){
return (function (state_10845){var state_val_10846 = (state_10845[(1)]);if((state_val_10846 === (7)))
{var inst_10794 = (state_10845[(7)]);var inst_10799 = cljs.core.apply.call(null,cljs.core.hash_map,inst_10794);var state_10845__$1 = state_10845;var statearr_10847_10888 = state_10845__$1;(statearr_10847_10888[(2)] = inst_10799);
(statearr_10847_10888[(1)] = (9));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (20)))
{var inst_10809 = (state_10845[(8)]);var state_10845__$1 = state_10845;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_10845__$1,(23),out,inst_10809);
} else
{if((state_val_10846 === (1)))
{var inst_10784 = (state_10845[(9)]);var inst_10784__$1 = calc_state.call(null);var inst_10785 = cljs.core.seq_QMARK_.call(null,inst_10784__$1);var state_10845__$1 = (function (){var statearr_10848 = state_10845;(statearr_10848[(9)] = inst_10784__$1);
return statearr_10848;
})();if(inst_10785)
{var statearr_10849_10889 = state_10845__$1;(statearr_10849_10889[(1)] = (2));
} else
{var statearr_10850_10890 = state_10845__$1;(statearr_10850_10890[(1)] = (3));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (4)))
{var inst_10784 = (state_10845[(9)]);var inst_10790 = (state_10845[(2)]);var inst_10791 = cljs.core.get.call(null,inst_10790,new cljs.core.Keyword(null,"reads","reads",-1215067361));var inst_10792 = cljs.core.get.call(null,inst_10790,new cljs.core.Keyword(null,"mutes","mutes",1068806309));var inst_10793 = cljs.core.get.call(null,inst_10790,new cljs.core.Keyword(null,"solos","solos",1441458643));var inst_10794 = inst_10784;var state_10845__$1 = (function (){var statearr_10851 = state_10845;(statearr_10851[(10)] = inst_10793);
(statearr_10851[(11)] = inst_10791);
(statearr_10851[(12)] = inst_10792);
(statearr_10851[(7)] = inst_10794);
return statearr_10851;
})();var statearr_10852_10891 = state_10845__$1;(statearr_10852_10891[(2)] = null);
(statearr_10852_10891[(1)] = (5));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (15)))
{var state_10845__$1 = state_10845;var statearr_10853_10892 = state_10845__$1;(statearr_10853_10892[(2)] = null);
(statearr_10853_10892[(1)] = (16));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (21)))
{var state_10845__$1 = state_10845;var statearr_10854_10893 = state_10845__$1;(statearr_10854_10893[(2)] = null);
(statearr_10854_10893[(1)] = (22));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (13)))
{var inst_10841 = (state_10845[(2)]);var state_10845__$1 = state_10845;var statearr_10855_10894 = state_10845__$1;(statearr_10855_10894[(2)] = inst_10841);
(statearr_10855_10894[(1)] = (6));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (22)))
{var inst_10802 = (state_10845[(13)]);var inst_10838 = (state_10845[(2)]);var inst_10794 = inst_10802;var state_10845__$1 = (function (){var statearr_10856 = state_10845;(statearr_10856[(14)] = inst_10838);
(statearr_10856[(7)] = inst_10794);
return statearr_10856;
})();var statearr_10857_10895 = state_10845__$1;(statearr_10857_10895[(2)] = null);
(statearr_10857_10895[(1)] = (5));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (6)))
{var inst_10843 = (state_10845[(2)]);var state_10845__$1 = state_10845;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_10845__$1,inst_10843);
} else
{if((state_val_10846 === (17)))
{var inst_10824 = (state_10845[(15)]);var state_10845__$1 = state_10845;var statearr_10858_10896 = state_10845__$1;(statearr_10858_10896[(2)] = inst_10824);
(statearr_10858_10896[(1)] = (19));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (3)))
{var inst_10784 = (state_10845[(9)]);var state_10845__$1 = state_10845;var statearr_10859_10897 = state_10845__$1;(statearr_10859_10897[(2)] = inst_10784);
(statearr_10859_10897[(1)] = (4));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (12)))
{var inst_10824 = (state_10845[(15)]);var inst_10810 = (state_10845[(16)]);var inst_10805 = (state_10845[(17)]);var inst_10824__$1 = inst_10805.call(null,inst_10810);var state_10845__$1 = (function (){var statearr_10860 = state_10845;(statearr_10860[(15)] = inst_10824__$1);
return statearr_10860;
})();if(cljs.core.truth_(inst_10824__$1))
{var statearr_10861_10898 = state_10845__$1;(statearr_10861_10898[(1)] = (17));
} else
{var statearr_10862_10899 = state_10845__$1;(statearr_10862_10899[(1)] = (18));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (2)))
{var inst_10784 = (state_10845[(9)]);var inst_10787 = cljs.core.apply.call(null,cljs.core.hash_map,inst_10784);var state_10845__$1 = state_10845;var statearr_10863_10900 = state_10845__$1;(statearr_10863_10900[(2)] = inst_10787);
(statearr_10863_10900[(1)] = (4));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (23)))
{var inst_10835 = (state_10845[(2)]);var state_10845__$1 = state_10845;var statearr_10864_10901 = state_10845__$1;(statearr_10864_10901[(2)] = inst_10835);
(statearr_10864_10901[(1)] = (22));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (19)))
{var inst_10832 = (state_10845[(2)]);var state_10845__$1 = state_10845;if(cljs.core.truth_(inst_10832))
{var statearr_10865_10902 = state_10845__$1;(statearr_10865_10902[(1)] = (20));
} else
{var statearr_10866_10903 = state_10845__$1;(statearr_10866_10903[(1)] = (21));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (11)))
{var inst_10809 = (state_10845[(8)]);var inst_10815 = (inst_10809 == null);var state_10845__$1 = state_10845;if(cljs.core.truth_(inst_10815))
{var statearr_10867_10904 = state_10845__$1;(statearr_10867_10904[(1)] = (14));
} else
{var statearr_10868_10905 = state_10845__$1;(statearr_10868_10905[(1)] = (15));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (9)))
{var inst_10802 = (state_10845[(13)]);var inst_10802__$1 = (state_10845[(2)]);var inst_10803 = cljs.core.get.call(null,inst_10802__$1,new cljs.core.Keyword(null,"reads","reads",-1215067361));var inst_10804 = cljs.core.get.call(null,inst_10802__$1,new cljs.core.Keyword(null,"mutes","mutes",1068806309));var inst_10805 = cljs.core.get.call(null,inst_10802__$1,new cljs.core.Keyword(null,"solos","solos",1441458643));var state_10845__$1 = (function (){var statearr_10869 = state_10845;(statearr_10869[(18)] = inst_10804);
(statearr_10869[(13)] = inst_10802__$1);
(statearr_10869[(17)] = inst_10805);
return statearr_10869;
})();return cljs.core.async.impl.ioc_helpers.ioc_alts_BANG_.call(null,state_10845__$1,(10),inst_10803);
} else
{if((state_val_10846 === (5)))
{var inst_10794 = (state_10845[(7)]);var inst_10797 = cljs.core.seq_QMARK_.call(null,inst_10794);var state_10845__$1 = state_10845;if(inst_10797)
{var statearr_10870_10906 = state_10845__$1;(statearr_10870_10906[(1)] = (7));
} else
{var statearr_10871_10907 = state_10845__$1;(statearr_10871_10907[(1)] = (8));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (14)))
{var inst_10810 = (state_10845[(16)]);var inst_10817 = cljs.core.swap_BANG_.call(null,cs,cljs.core.dissoc,inst_10810);var state_10845__$1 = state_10845;var statearr_10872_10908 = state_10845__$1;(statearr_10872_10908[(2)] = inst_10817);
(statearr_10872_10908[(1)] = (16));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (16)))
{var inst_10820 = (state_10845[(2)]);var inst_10821 = calc_state.call(null);var inst_10794 = inst_10821;var state_10845__$1 = (function (){var statearr_10873 = state_10845;(statearr_10873[(19)] = inst_10820);
(statearr_10873[(7)] = inst_10794);
return statearr_10873;
})();var statearr_10874_10909 = state_10845__$1;(statearr_10874_10909[(2)] = null);
(statearr_10874_10909[(1)] = (5));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (10)))
{var inst_10810 = (state_10845[(16)]);var inst_10809 = (state_10845[(8)]);var inst_10808 = (state_10845[(2)]);var inst_10809__$1 = cljs.core.nth.call(null,inst_10808,(0),null);var inst_10810__$1 = cljs.core.nth.call(null,inst_10808,(1),null);var inst_10811 = (inst_10809__$1 == null);var inst_10812 = cljs.core._EQ_.call(null,inst_10810__$1,change);var inst_10813 = (inst_10811) || (inst_10812);var state_10845__$1 = (function (){var statearr_10875 = state_10845;(statearr_10875[(16)] = inst_10810__$1);
(statearr_10875[(8)] = inst_10809__$1);
return statearr_10875;
})();if(cljs.core.truth_(inst_10813))
{var statearr_10876_10910 = state_10845__$1;(statearr_10876_10910[(1)] = (11));
} else
{var statearr_10877_10911 = state_10845__$1;(statearr_10877_10911[(1)] = (12));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (18)))
{var inst_10804 = (state_10845[(18)]);var inst_10810 = (state_10845[(16)]);var inst_10805 = (state_10845[(17)]);var inst_10827 = cljs.core.empty_QMARK_.call(null,inst_10805);var inst_10828 = inst_10804.call(null,inst_10810);var inst_10829 = cljs.core.not.call(null,inst_10828);var inst_10830 = (inst_10827) && (inst_10829);var state_10845__$1 = state_10845;var statearr_10878_10912 = state_10845__$1;(statearr_10878_10912[(2)] = inst_10830);
(statearr_10878_10912[(1)] = (19));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_10846 === (8)))
{var inst_10794 = (state_10845[(7)]);var state_10845__$1 = state_10845;var statearr_10879_10913 = state_10845__$1;(statearr_10879_10913[(2)] = inst_10794);
(statearr_10879_10913[(1)] = (9));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___10887,cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state,m))
;return ((function (switch__6338__auto__,c__6353__auto___10887,cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state,m){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_10883 = [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null];(statearr_10883[(0)] = state_machine__6339__auto__);
(statearr_10883[(1)] = (1));
return statearr_10883;
});
var state_machine__6339__auto____1 = (function (state_10845){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_10845);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e10884){if((e10884 instanceof Object))
{var ex__6342__auto__ = e10884;var statearr_10885_10914 = state_10845;(statearr_10885_10914[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_10845);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e10884;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__10915 = state_10845;
state_10845 = G__10915;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_10845){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_10845);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___10887,cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state,m))
})();var state__6355__auto__ = (function (){var statearr_10886 = f__6354__auto__.call(null);(statearr_10886[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___10887);
return statearr_10886;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___10887,cs,solo_modes,attrs,solo_mode,change,changed,pick,calc_state,m))
);
return m;
});
/**
* Adds ch as an input to the mix
*/
cljs.core.async.admix = (function admix(mix,ch){return cljs.core.async.admix_STAR_.call(null,mix,ch);
});
/**
* Removes ch as an input to the mix
*/
cljs.core.async.unmix = (function unmix(mix,ch){return cljs.core.async.unmix_STAR_.call(null,mix,ch);
});
/**
* removes all inputs from the mix
*/
cljs.core.async.unmix_all = (function unmix_all(mix){return cljs.core.async.unmix_all_STAR_.call(null,mix);
});
/**
* Atomically sets the state(s) of one or more channels in a mix. The
* state map is a map of channels -> channel-state-map. A
* channel-state-map is a map of attrs -> boolean, where attr is one or
* more of :mute, :pause or :solo. Any states supplied are merged with
* the current state.
* 
* Note that channels can be added to a mix via toggle, which can be
* used to add channels in a particular (e.g. paused) state.
*/
cljs.core.async.toggle = (function toggle(mix,state_map){return cljs.core.async.toggle_STAR_.call(null,mix,state_map);
});
/**
* Sets the solo mode of the mix. mode must be one of :mute or :pause
*/
cljs.core.async.solo_mode = (function solo_mode(mix,mode){return cljs.core.async.solo_mode_STAR_.call(null,mix,mode);
});
cljs.core.async.Pub = (function (){var obj10917 = {};return obj10917;
})();
cljs.core.async.sub_STAR_ = (function sub_STAR_(p,v,ch,close_QMARK_){if((function (){var and__3539__auto__ = p;if(and__3539__auto__)
{return p.cljs$core$async$Pub$sub_STAR_$arity$4;
} else
{return and__3539__auto__;
}
})())
{return p.cljs$core$async$Pub$sub_STAR_$arity$4(p,v,ch,close_QMARK_);
} else
{var x__4178__auto__ = (((p == null))?null:p);return (function (){var or__3551__auto__ = (cljs.core.async.sub_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.sub_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Pub.sub*",p);
}
}
})().call(null,p,v,ch,close_QMARK_);
}
});
cljs.core.async.unsub_STAR_ = (function unsub_STAR_(p,v,ch){if((function (){var and__3539__auto__ = p;if(and__3539__auto__)
{return p.cljs$core$async$Pub$unsub_STAR_$arity$3;
} else
{return and__3539__auto__;
}
})())
{return p.cljs$core$async$Pub$unsub_STAR_$arity$3(p,v,ch);
} else
{var x__4178__auto__ = (((p == null))?null:p);return (function (){var or__3551__auto__ = (cljs.core.async.unsub_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.unsub_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Pub.unsub*",p);
}
}
})().call(null,p,v,ch);
}
});
cljs.core.async.unsub_all_STAR_ = (function() {
var unsub_all_STAR_ = null;
var unsub_all_STAR___1 = (function (p){if((function (){var and__3539__auto__ = p;if(and__3539__auto__)
{return p.cljs$core$async$Pub$unsub_all_STAR_$arity$1;
} else
{return and__3539__auto__;
}
})())
{return p.cljs$core$async$Pub$unsub_all_STAR_$arity$1(p);
} else
{var x__4178__auto__ = (((p == null))?null:p);return (function (){var or__3551__auto__ = (cljs.core.async.unsub_all_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.unsub_all_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Pub.unsub-all*",p);
}
}
})().call(null,p);
}
});
var unsub_all_STAR___2 = (function (p,v){if((function (){var and__3539__auto__ = p;if(and__3539__auto__)
{return p.cljs$core$async$Pub$unsub_all_STAR_$arity$2;
} else
{return and__3539__auto__;
}
})())
{return p.cljs$core$async$Pub$unsub_all_STAR_$arity$2(p,v);
} else
{var x__4178__auto__ = (((p == null))?null:p);return (function (){var or__3551__auto__ = (cljs.core.async.unsub_all_STAR_[goog.typeOf(x__4178__auto__)]);if(or__3551__auto__)
{return or__3551__auto__;
} else
{var or__3551__auto____$1 = (cljs.core.async.unsub_all_STAR_["_"]);if(or__3551__auto____$1)
{return or__3551__auto____$1;
} else
{throw cljs.core.missing_protocol.call(null,"Pub.unsub-all*",p);
}
}
})().call(null,p,v);
}
});
unsub_all_STAR_ = function(p,v){
switch(arguments.length){
case 1:
return unsub_all_STAR___1.call(this,p);
case 2:
return unsub_all_STAR___2.call(this,p,v);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
unsub_all_STAR_.cljs$core$IFn$_invoke$arity$1 = unsub_all_STAR___1;
unsub_all_STAR_.cljs$core$IFn$_invoke$arity$2 = unsub_all_STAR___2;
return unsub_all_STAR_;
})()
;
/**
* Creates and returns a pub(lication) of the supplied channel,
* partitioned into topics by the topic-fn. topic-fn will be applied to
* each value on the channel and the result will determine the 'topic'
* on which that value will be put. Channels can be subscribed to
* receive copies of topics using 'sub', and unsubscribed using
* 'unsub'. Each topic will be handled by an internal mult on a
* dedicated channel. By default these internal channels are
* unbuffered, but a buf-fn can be supplied which, given a topic,
* creates a buffer with desired properties.
* 
* Each item is distributed to all subs in parallel and synchronously,
* i.e. each sub must accept before the next item is distributed. Use
* buffering/windowing to prevent slow subs from holding up the pub.
* 
* Items received when there are no matching subs get dropped.
* 
* Note that if buf-fns are used then each topic is handled
* asynchronously, i.e. if a channel is subscribed to more than one
* topic it should not expect them to be interleaved identically with
* the source.
*/
cljs.core.async.pub = (function() {
var pub = null;
var pub__2 = (function (ch,topic_fn){return pub.call(null,ch,topic_fn,cljs.core.constantly.call(null,null));
});
var pub__3 = (function (ch,topic_fn,buf_fn){var mults = cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY);var ensure_mult = ((function (mults){
return (function (topic){var or__3551__auto__ = cljs.core.get.call(null,cljs.core.deref.call(null,mults),topic);if(cljs.core.truth_(or__3551__auto__))
{return or__3551__auto__;
} else
{return cljs.core.get.call(null,cljs.core.swap_BANG_.call(null,mults,((function (or__3551__auto__,mults){
return (function (p1__10918_SHARP_){if(cljs.core.truth_(p1__10918_SHARP_.call(null,topic)))
{return p1__10918_SHARP_;
} else
{return cljs.core.assoc.call(null,p1__10918_SHARP_,topic,cljs.core.async.mult.call(null,cljs.core.async.chan.call(null,buf_fn.call(null,topic))));
}
});})(or__3551__auto__,mults))
),topic);
}
});})(mults))
;var p = (function (){if(typeof cljs.core.async.t11043 !== 'undefined')
{} else
{
/**
* @constructor
*/
cljs.core.async.t11043 = (function (ensure_mult,mults,buf_fn,topic_fn,ch,pub,meta11044){
this.ensure_mult = ensure_mult;
this.mults = mults;
this.buf_fn = buf_fn;
this.topic_fn = topic_fn;
this.ch = ch;
this.pub = pub;
this.meta11044 = meta11044;
this.cljs$lang$protocol_mask$partition1$ = 0;
this.cljs$lang$protocol_mask$partition0$ = 393216;
})
cljs.core.async.t11043.cljs$lang$type = true;
cljs.core.async.t11043.cljs$lang$ctorStr = "cljs.core.async/t11043";
cljs.core.async.t11043.cljs$lang$ctorPrWriter = ((function (mults,ensure_mult){
return (function (this__4118__auto__,writer__4119__auto__,opt__4120__auto__){return cljs.core._write.call(null,writer__4119__auto__,"cljs.core.async/t11043");
});})(mults,ensure_mult))
;
cljs.core.async.t11043.prototype.cljs$core$async$Pub$ = true;
cljs.core.async.t11043.prototype.cljs$core$async$Pub$sub_STAR_$arity$4 = ((function (mults,ensure_mult){
return (function (p,topic,ch__$2,close_QMARK_){var self__ = this;
var p__$1 = this;var m = self__.ensure_mult.call(null,topic);return cljs.core.async.tap.call(null,m,ch__$2,close_QMARK_);
});})(mults,ensure_mult))
;
cljs.core.async.t11043.prototype.cljs$core$async$Pub$unsub_STAR_$arity$3 = ((function (mults,ensure_mult){
return (function (p,topic,ch__$2){var self__ = this;
var p__$1 = this;var temp__4126__auto__ = cljs.core.get.call(null,cljs.core.deref.call(null,self__.mults),topic);if(cljs.core.truth_(temp__4126__auto__))
{var m = temp__4126__auto__;return cljs.core.async.untap.call(null,m,ch__$2);
} else
{return null;
}
});})(mults,ensure_mult))
;
cljs.core.async.t11043.prototype.cljs$core$async$Pub$unsub_all_STAR_$arity$1 = ((function (mults,ensure_mult){
return (function (_){var self__ = this;
var ___$1 = this;return cljs.core.reset_BANG_.call(null,self__.mults,cljs.core.PersistentArrayMap.EMPTY);
});})(mults,ensure_mult))
;
cljs.core.async.t11043.prototype.cljs$core$async$Pub$unsub_all_STAR_$arity$2 = ((function (mults,ensure_mult){
return (function (_,topic){var self__ = this;
var ___$1 = this;return cljs.core.swap_BANG_.call(null,self__.mults,cljs.core.dissoc,topic);
});})(mults,ensure_mult))
;
cljs.core.async.t11043.prototype.cljs$core$async$Mux$ = true;
cljs.core.async.t11043.prototype.cljs$core$async$Mux$muxch_STAR_$arity$1 = ((function (mults,ensure_mult){
return (function (_){var self__ = this;
var ___$1 = this;return self__.ch;
});})(mults,ensure_mult))
;
cljs.core.async.t11043.prototype.cljs$core$IMeta$_meta$arity$1 = ((function (mults,ensure_mult){
return (function (_11045){var self__ = this;
var _11045__$1 = this;return self__.meta11044;
});})(mults,ensure_mult))
;
cljs.core.async.t11043.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = ((function (mults,ensure_mult){
return (function (_11045,meta11044__$1){var self__ = this;
var _11045__$1 = this;return (new cljs.core.async.t11043(self__.ensure_mult,self__.mults,self__.buf_fn,self__.topic_fn,self__.ch,self__.pub,meta11044__$1));
});})(mults,ensure_mult))
;
cljs.core.async.__GT_t11043 = ((function (mults,ensure_mult){
return (function __GT_t11043(ensure_mult__$1,mults__$1,buf_fn__$1,topic_fn__$1,ch__$1,pub__$1,meta11044){return (new cljs.core.async.t11043(ensure_mult__$1,mults__$1,buf_fn__$1,topic_fn__$1,ch__$1,pub__$1,meta11044));
});})(mults,ensure_mult))
;
}
return (new cljs.core.async.t11043(ensure_mult,mults,buf_fn,topic_fn,ch,pub,null));
})();var c__6353__auto___11167 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___11167,mults,ensure_mult,p){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___11167,mults,ensure_mult,p){
return (function (state_11119){var state_val_11120 = (state_11119[(1)]);if((state_val_11120 === (7)))
{var inst_11115 = (state_11119[(2)]);var state_11119__$1 = state_11119;var statearr_11121_11168 = state_11119__$1;(statearr_11121_11168[(2)] = inst_11115);
(statearr_11121_11168[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (20)))
{var state_11119__$1 = state_11119;var statearr_11122_11169 = state_11119__$1;(statearr_11122_11169[(2)] = null);
(statearr_11122_11169[(1)] = (21));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (1)))
{var state_11119__$1 = state_11119;var statearr_11123_11170 = state_11119__$1;(statearr_11123_11170[(2)] = null);
(statearr_11123_11170[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (24)))
{var inst_11048 = (state_11119[(7)]);var inst_11098 = (state_11119[(8)]);var _ = cljs.core.async.impl.ioc_helpers.add_exception_frame.call(null,state_11119,(23),Object,null,(22));var inst_11105 = cljs.core.async.muxch_STAR_.call(null,inst_11098);var state_11119__$1 = state_11119;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11119__$1,(25),inst_11105,inst_11048);
} else
{if((state_val_11120 === (4)))
{var inst_11048 = (state_11119[(7)]);var inst_11048__$1 = (state_11119[(2)]);var inst_11049 = (inst_11048__$1 == null);var state_11119__$1 = (function (){var statearr_11124 = state_11119;(statearr_11124[(7)] = inst_11048__$1);
return statearr_11124;
})();if(cljs.core.truth_(inst_11049))
{var statearr_11125_11171 = state_11119__$1;(statearr_11125_11171[(1)] = (5));
} else
{var statearr_11126_11172 = state_11119__$1;(statearr_11126_11172[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (15)))
{var inst_11090 = (state_11119[(2)]);var state_11119__$1 = state_11119;var statearr_11127_11173 = state_11119__$1;(statearr_11127_11173[(2)] = inst_11090);
(statearr_11127_11173[(1)] = (12));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (21)))
{var inst_11112 = (state_11119[(2)]);var state_11119__$1 = (function (){var statearr_11128 = state_11119;(statearr_11128[(9)] = inst_11112);
return statearr_11128;
})();var statearr_11129_11174 = state_11119__$1;(statearr_11129_11174[(2)] = null);
(statearr_11129_11174[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (13)))
{var inst_11072 = (state_11119[(10)]);var inst_11074 = cljs.core.chunked_seq_QMARK_.call(null,inst_11072);var state_11119__$1 = state_11119;if(inst_11074)
{var statearr_11130_11175 = state_11119__$1;(statearr_11130_11175[(1)] = (16));
} else
{var statearr_11131_11176 = state_11119__$1;(statearr_11131_11176[(1)] = (17));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (22)))
{var inst_11109 = (state_11119[(2)]);var state_11119__$1 = state_11119;var statearr_11132_11177 = state_11119__$1;(statearr_11132_11177[(2)] = inst_11109);
(statearr_11132_11177[(1)] = (21));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (6)))
{var inst_11048 = (state_11119[(7)]);var inst_11096 = (state_11119[(11)]);var inst_11098 = (state_11119[(8)]);var inst_11096__$1 = topic_fn.call(null,inst_11048);var inst_11097 = cljs.core.deref.call(null,mults);var inst_11098__$1 = cljs.core.get.call(null,inst_11097,inst_11096__$1);var state_11119__$1 = (function (){var statearr_11133 = state_11119;(statearr_11133[(11)] = inst_11096__$1);
(statearr_11133[(8)] = inst_11098__$1);
return statearr_11133;
})();if(cljs.core.truth_(inst_11098__$1))
{var statearr_11134_11178 = state_11119__$1;(statearr_11134_11178[(1)] = (19));
} else
{var statearr_11135_11179 = state_11119__$1;(statearr_11135_11179[(1)] = (20));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (25)))
{var inst_11107 = (state_11119[(2)]);var state_11119__$1 = state_11119;var statearr_11136_11180 = state_11119__$1;(statearr_11136_11180[(2)] = inst_11107);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11119__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (17)))
{var inst_11072 = (state_11119[(10)]);var inst_11081 = cljs.core.first.call(null,inst_11072);var inst_11082 = cljs.core.async.muxch_STAR_.call(null,inst_11081);var inst_11083 = cljs.core.async.close_BANG_.call(null,inst_11082);var inst_11084 = cljs.core.next.call(null,inst_11072);var inst_11058 = inst_11084;var inst_11059 = null;var inst_11060 = (0);var inst_11061 = (0);var state_11119__$1 = (function (){var statearr_11137 = state_11119;(statearr_11137[(12)] = inst_11083);
(statearr_11137[(13)] = inst_11058);
(statearr_11137[(14)] = inst_11060);
(statearr_11137[(15)] = inst_11059);
(statearr_11137[(16)] = inst_11061);
return statearr_11137;
})();var statearr_11138_11181 = state_11119__$1;(statearr_11138_11181[(2)] = null);
(statearr_11138_11181[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (3)))
{var inst_11117 = (state_11119[(2)]);var state_11119__$1 = state_11119;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_11119__$1,inst_11117);
} else
{if((state_val_11120 === (12)))
{var inst_11092 = (state_11119[(2)]);var state_11119__$1 = state_11119;var statearr_11139_11182 = state_11119__$1;(statearr_11139_11182[(2)] = inst_11092);
(statearr_11139_11182[(1)] = (9));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (2)))
{var state_11119__$1 = state_11119;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_11119__$1,(4),ch);
} else
{if((state_val_11120 === (23)))
{var inst_11096 = (state_11119[(11)]);var inst_11100 = (state_11119[(2)]);var inst_11101 = cljs.core.swap_BANG_.call(null,mults,cljs.core.dissoc,inst_11096);var state_11119__$1 = (function (){var statearr_11140 = state_11119;(statearr_11140[(17)] = inst_11100);
return statearr_11140;
})();var statearr_11141_11183 = state_11119__$1;(statearr_11141_11183[(2)] = inst_11101);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11119__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (19)))
{var state_11119__$1 = state_11119;var statearr_11142_11184 = state_11119__$1;(statearr_11142_11184[(2)] = null);
(statearr_11142_11184[(1)] = (24));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (11)))
{var inst_11058 = (state_11119[(13)]);var inst_11072 = (state_11119[(10)]);var inst_11072__$1 = cljs.core.seq.call(null,inst_11058);var state_11119__$1 = (function (){var statearr_11143 = state_11119;(statearr_11143[(10)] = inst_11072__$1);
return statearr_11143;
})();if(inst_11072__$1)
{var statearr_11144_11185 = state_11119__$1;(statearr_11144_11185[(1)] = (13));
} else
{var statearr_11145_11186 = state_11119__$1;(statearr_11145_11186[(1)] = (14));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (9)))
{var inst_11094 = (state_11119[(2)]);var state_11119__$1 = state_11119;var statearr_11146_11187 = state_11119__$1;(statearr_11146_11187[(2)] = inst_11094);
(statearr_11146_11187[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (5)))
{var inst_11055 = cljs.core.deref.call(null,mults);var inst_11056 = cljs.core.vals.call(null,inst_11055);var inst_11057 = cljs.core.seq.call(null,inst_11056);var inst_11058 = inst_11057;var inst_11059 = null;var inst_11060 = (0);var inst_11061 = (0);var state_11119__$1 = (function (){var statearr_11147 = state_11119;(statearr_11147[(13)] = inst_11058);
(statearr_11147[(14)] = inst_11060);
(statearr_11147[(15)] = inst_11059);
(statearr_11147[(16)] = inst_11061);
return statearr_11147;
})();var statearr_11148_11188 = state_11119__$1;(statearr_11148_11188[(2)] = null);
(statearr_11148_11188[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (14)))
{var state_11119__$1 = state_11119;var statearr_11152_11189 = state_11119__$1;(statearr_11152_11189[(2)] = null);
(statearr_11152_11189[(1)] = (15));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (16)))
{var inst_11072 = (state_11119[(10)]);var inst_11076 = cljs.core.chunk_first.call(null,inst_11072);var inst_11077 = cljs.core.chunk_rest.call(null,inst_11072);var inst_11078 = cljs.core.count.call(null,inst_11076);var inst_11058 = inst_11077;var inst_11059 = inst_11076;var inst_11060 = inst_11078;var inst_11061 = (0);var state_11119__$1 = (function (){var statearr_11153 = state_11119;(statearr_11153[(13)] = inst_11058);
(statearr_11153[(14)] = inst_11060);
(statearr_11153[(15)] = inst_11059);
(statearr_11153[(16)] = inst_11061);
return statearr_11153;
})();var statearr_11154_11190 = state_11119__$1;(statearr_11154_11190[(2)] = null);
(statearr_11154_11190[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (10)))
{var inst_11058 = (state_11119[(13)]);var inst_11060 = (state_11119[(14)]);var inst_11059 = (state_11119[(15)]);var inst_11061 = (state_11119[(16)]);var inst_11066 = cljs.core._nth.call(null,inst_11059,inst_11061);var inst_11067 = cljs.core.async.muxch_STAR_.call(null,inst_11066);var inst_11068 = cljs.core.async.close_BANG_.call(null,inst_11067);var inst_11069 = (inst_11061 + (1));var tmp11149 = inst_11058;var tmp11150 = inst_11060;var tmp11151 = inst_11059;var inst_11058__$1 = tmp11149;var inst_11059__$1 = tmp11151;var inst_11060__$1 = tmp11150;var inst_11061__$1 = inst_11069;var state_11119__$1 = (function (){var statearr_11155 = state_11119;(statearr_11155[(13)] = inst_11058__$1);
(statearr_11155[(14)] = inst_11060__$1);
(statearr_11155[(18)] = inst_11068);
(statearr_11155[(15)] = inst_11059__$1);
(statearr_11155[(16)] = inst_11061__$1);
return statearr_11155;
})();var statearr_11156_11191 = state_11119__$1;(statearr_11156_11191[(2)] = null);
(statearr_11156_11191[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (18)))
{var inst_11087 = (state_11119[(2)]);var state_11119__$1 = state_11119;var statearr_11157_11192 = state_11119__$1;(statearr_11157_11192[(2)] = inst_11087);
(statearr_11157_11192[(1)] = (15));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11120 === (8)))
{var inst_11060 = (state_11119[(14)]);var inst_11061 = (state_11119[(16)]);var inst_11063 = (inst_11061 < inst_11060);var inst_11064 = inst_11063;var state_11119__$1 = state_11119;if(cljs.core.truth_(inst_11064))
{var statearr_11158_11193 = state_11119__$1;(statearr_11158_11193[(1)] = (10));
} else
{var statearr_11159_11194 = state_11119__$1;(statearr_11159_11194[(1)] = (11));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___11167,mults,ensure_mult,p))
;return ((function (switch__6338__auto__,c__6353__auto___11167,mults,ensure_mult,p){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_11163 = [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null];(statearr_11163[(0)] = state_machine__6339__auto__);
(statearr_11163[(1)] = (1));
return statearr_11163;
});
var state_machine__6339__auto____1 = (function (state_11119){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_11119);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e11164){if((e11164 instanceof Object))
{var ex__6342__auto__ = e11164;var statearr_11165_11195 = state_11119;(statearr_11165_11195[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11119);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e11164;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__11196 = state_11119;
state_11119 = G__11196;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_11119){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_11119);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___11167,mults,ensure_mult,p))
})();var state__6355__auto__ = (function (){var statearr_11166 = f__6354__auto__.call(null);(statearr_11166[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___11167);
return statearr_11166;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___11167,mults,ensure_mult,p))
);
return p;
});
pub = function(ch,topic_fn,buf_fn){
switch(arguments.length){
case 2:
return pub__2.call(this,ch,topic_fn);
case 3:
return pub__3.call(this,ch,topic_fn,buf_fn);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
pub.cljs$core$IFn$_invoke$arity$2 = pub__2;
pub.cljs$core$IFn$_invoke$arity$3 = pub__3;
return pub;
})()
;
/**
* Subscribes a channel to a topic of a pub.
* 
* By default the channel will be closed when the source closes,
* but can be determined by the close? parameter.
*/
cljs.core.async.sub = (function() {
var sub = null;
var sub__3 = (function (p,topic,ch){return sub.call(null,p,topic,ch,true);
});
var sub__4 = (function (p,topic,ch,close_QMARK_){return cljs.core.async.sub_STAR_.call(null,p,topic,ch,close_QMARK_);
});
sub = function(p,topic,ch,close_QMARK_){
switch(arguments.length){
case 3:
return sub__3.call(this,p,topic,ch);
case 4:
return sub__4.call(this,p,topic,ch,close_QMARK_);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
sub.cljs$core$IFn$_invoke$arity$3 = sub__3;
sub.cljs$core$IFn$_invoke$arity$4 = sub__4;
return sub;
})()
;
/**
* Unsubscribes a channel from a topic of a pub
*/
cljs.core.async.unsub = (function unsub(p,topic,ch){return cljs.core.async.unsub_STAR_.call(null,p,topic,ch);
});
/**
* Unsubscribes all channels from a pub, or a topic of a pub
*/
cljs.core.async.unsub_all = (function() {
var unsub_all = null;
var unsub_all__1 = (function (p){return cljs.core.async.unsub_all_STAR_.call(null,p);
});
var unsub_all__2 = (function (p,topic){return cljs.core.async.unsub_all_STAR_.call(null,p,topic);
});
unsub_all = function(p,topic){
switch(arguments.length){
case 1:
return unsub_all__1.call(this,p);
case 2:
return unsub_all__2.call(this,p,topic);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
unsub_all.cljs$core$IFn$_invoke$arity$1 = unsub_all__1;
unsub_all.cljs$core$IFn$_invoke$arity$2 = unsub_all__2;
return unsub_all;
})()
;
/**
* Takes a function and a collection of source channels, and returns a
* channel which contains the values produced by applying f to the set
* of first items taken from each source channel, followed by applying
* f to the set of second items from each channel, until any one of the
* channels is closed, at which point the output channel will be
* closed. The returned channel will be unbuffered by default, or a
* buf-or-n can be supplied
*/
cljs.core.async.map = (function() {
var map = null;
var map__2 = (function (f,chs){return map.call(null,f,chs,null);
});
var map__3 = (function (f,chs,buf_or_n){var chs__$1 = cljs.core.vec.call(null,chs);var out = cljs.core.async.chan.call(null,buf_or_n);var cnt = cljs.core.count.call(null,chs__$1);var rets = cljs.core.object_array.call(null,cnt);var dchan = cljs.core.async.chan.call(null,(1));var dctr = cljs.core.atom.call(null,null);var done = cljs.core.mapv.call(null,((function (chs__$1,out,cnt,rets,dchan,dctr){
return (function (i){return ((function (chs__$1,out,cnt,rets,dchan,dctr){
return (function (ret){(rets[i] = ret);
if((cljs.core.swap_BANG_.call(null,dctr,cljs.core.dec) === (0)))
{return cljs.core.async.put_BANG_.call(null,dchan,rets.slice((0)));
} else
{return null;
}
});
;})(chs__$1,out,cnt,rets,dchan,dctr))
});})(chs__$1,out,cnt,rets,dchan,dctr))
,cljs.core.range.call(null,cnt));var c__6353__auto___11333 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___11333,chs__$1,out,cnt,rets,dchan,dctr,done){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___11333,chs__$1,out,cnt,rets,dchan,dctr,done){
return (function (state_11303){var state_val_11304 = (state_11303[(1)]);if((state_val_11304 === (7)))
{var state_11303__$1 = state_11303;var statearr_11305_11334 = state_11303__$1;(statearr_11305_11334[(2)] = null);
(statearr_11305_11334[(1)] = (8));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (1)))
{var state_11303__$1 = state_11303;var statearr_11306_11335 = state_11303__$1;(statearr_11306_11335[(2)] = null);
(statearr_11306_11335[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (4)))
{var inst_11267 = (state_11303[(7)]);var inst_11269 = (inst_11267 < cnt);var state_11303__$1 = state_11303;if(cljs.core.truth_(inst_11269))
{var statearr_11307_11336 = state_11303__$1;(statearr_11307_11336[(1)] = (6));
} else
{var statearr_11308_11337 = state_11303__$1;(statearr_11308_11337[(1)] = (7));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (15)))
{var inst_11299 = (state_11303[(2)]);var state_11303__$1 = state_11303;var statearr_11309_11338 = state_11303__$1;(statearr_11309_11338[(2)] = inst_11299);
(statearr_11309_11338[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (13)))
{var inst_11292 = cljs.core.async.close_BANG_.call(null,out);var state_11303__$1 = state_11303;var statearr_11310_11339 = state_11303__$1;(statearr_11310_11339[(2)] = inst_11292);
(statearr_11310_11339[(1)] = (15));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (6)))
{var state_11303__$1 = state_11303;var statearr_11311_11340 = state_11303__$1;(statearr_11311_11340[(2)] = null);
(statearr_11311_11340[(1)] = (11));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (3)))
{var inst_11301 = (state_11303[(2)]);var state_11303__$1 = state_11303;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_11303__$1,inst_11301);
} else
{if((state_val_11304 === (12)))
{var inst_11289 = (state_11303[(8)]);var inst_11289__$1 = (state_11303[(2)]);var inst_11290 = cljs.core.some.call(null,cljs.core.nil_QMARK_,inst_11289__$1);var state_11303__$1 = (function (){var statearr_11312 = state_11303;(statearr_11312[(8)] = inst_11289__$1);
return statearr_11312;
})();if(cljs.core.truth_(inst_11290))
{var statearr_11313_11341 = state_11303__$1;(statearr_11313_11341[(1)] = (13));
} else
{var statearr_11314_11342 = state_11303__$1;(statearr_11314_11342[(1)] = (14));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (2)))
{var inst_11266 = cljs.core.reset_BANG_.call(null,dctr,cnt);var inst_11267 = (0);var state_11303__$1 = (function (){var statearr_11315 = state_11303;(statearr_11315[(7)] = inst_11267);
(statearr_11315[(9)] = inst_11266);
return statearr_11315;
})();var statearr_11316_11343 = state_11303__$1;(statearr_11316_11343[(2)] = null);
(statearr_11316_11343[(1)] = (4));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (11)))
{var inst_11267 = (state_11303[(7)]);var _ = cljs.core.async.impl.ioc_helpers.add_exception_frame.call(null,state_11303,(10),Object,null,(9));var inst_11276 = chs__$1.call(null,inst_11267);var inst_11277 = done.call(null,inst_11267);var inst_11278 = cljs.core.async.take_BANG_.call(null,inst_11276,inst_11277);var state_11303__$1 = state_11303;var statearr_11317_11344 = state_11303__$1;(statearr_11317_11344[(2)] = inst_11278);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11303__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (9)))
{var inst_11267 = (state_11303[(7)]);var inst_11280 = (state_11303[(2)]);var inst_11281 = (inst_11267 + (1));var inst_11267__$1 = inst_11281;var state_11303__$1 = (function (){var statearr_11318 = state_11303;(statearr_11318[(7)] = inst_11267__$1);
(statearr_11318[(10)] = inst_11280);
return statearr_11318;
})();var statearr_11319_11345 = state_11303__$1;(statearr_11319_11345[(2)] = null);
(statearr_11319_11345[(1)] = (4));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (5)))
{var inst_11287 = (state_11303[(2)]);var state_11303__$1 = (function (){var statearr_11320 = state_11303;(statearr_11320[(11)] = inst_11287);
return statearr_11320;
})();return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_11303__$1,(12),dchan);
} else
{if((state_val_11304 === (14)))
{var inst_11289 = (state_11303[(8)]);var inst_11294 = cljs.core.apply.call(null,f,inst_11289);var state_11303__$1 = state_11303;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11303__$1,(16),out,inst_11294);
} else
{if((state_val_11304 === (16)))
{var inst_11296 = (state_11303[(2)]);var state_11303__$1 = (function (){var statearr_11321 = state_11303;(statearr_11321[(12)] = inst_11296);
return statearr_11321;
})();var statearr_11322_11346 = state_11303__$1;(statearr_11322_11346[(2)] = null);
(statearr_11322_11346[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (10)))
{var inst_11271 = (state_11303[(2)]);var inst_11272 = cljs.core.swap_BANG_.call(null,dctr,cljs.core.dec);var state_11303__$1 = (function (){var statearr_11323 = state_11303;(statearr_11323[(13)] = inst_11271);
return statearr_11323;
})();var statearr_11324_11347 = state_11303__$1;(statearr_11324_11347[(2)] = inst_11272);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11303__$1);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11304 === (8)))
{var inst_11285 = (state_11303[(2)]);var state_11303__$1 = state_11303;var statearr_11325_11348 = state_11303__$1;(statearr_11325_11348[(2)] = inst_11285);
(statearr_11325_11348[(1)] = (5));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___11333,chs__$1,out,cnt,rets,dchan,dctr,done))
;return ((function (switch__6338__auto__,c__6353__auto___11333,chs__$1,out,cnt,rets,dchan,dctr,done){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_11329 = [null,null,null,null,null,null,null,null,null,null,null,null,null,null];(statearr_11329[(0)] = state_machine__6339__auto__);
(statearr_11329[(1)] = (1));
return statearr_11329;
});
var state_machine__6339__auto____1 = (function (state_11303){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_11303);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e11330){if((e11330 instanceof Object))
{var ex__6342__auto__ = e11330;var statearr_11331_11349 = state_11303;(statearr_11331_11349[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11303);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e11330;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__11350 = state_11303;
state_11303 = G__11350;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_11303){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_11303);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___11333,chs__$1,out,cnt,rets,dchan,dctr,done))
})();var state__6355__auto__ = (function (){var statearr_11332 = f__6354__auto__.call(null);(statearr_11332[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___11333);
return statearr_11332;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___11333,chs__$1,out,cnt,rets,dchan,dctr,done))
);
return out;
});
map = function(f,chs,buf_or_n){
switch(arguments.length){
case 2:
return map__2.call(this,f,chs);
case 3:
return map__3.call(this,f,chs,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
map.cljs$core$IFn$_invoke$arity$2 = map__2;
map.cljs$core$IFn$_invoke$arity$3 = map__3;
return map;
})()
;
/**
* Takes a collection of source channels and returns a channel which
* contains all values taken from them. The returned channel will be
* unbuffered by default, or a buf-or-n can be supplied. The channel
* will close after all the source channels have closed.
*/
cljs.core.async.merge = (function() {
var merge = null;
var merge__1 = (function (chs){return merge.call(null,chs,null);
});
var merge__2 = (function (chs,buf_or_n){var out = cljs.core.async.chan.call(null,buf_or_n);var c__6353__auto___11458 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___11458,out){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___11458,out){
return (function (state_11434){var state_val_11435 = (state_11434[(1)]);if((state_val_11435 === (7)))
{var inst_11413 = (state_11434[(7)]);var inst_11414 = (state_11434[(8)]);var inst_11413__$1 = (state_11434[(2)]);var inst_11414__$1 = cljs.core.nth.call(null,inst_11413__$1,(0),null);var inst_11415 = cljs.core.nth.call(null,inst_11413__$1,(1),null);var inst_11416 = (inst_11414__$1 == null);var state_11434__$1 = (function (){var statearr_11436 = state_11434;(statearr_11436[(7)] = inst_11413__$1);
(statearr_11436[(9)] = inst_11415);
(statearr_11436[(8)] = inst_11414__$1);
return statearr_11436;
})();if(cljs.core.truth_(inst_11416))
{var statearr_11437_11459 = state_11434__$1;(statearr_11437_11459[(1)] = (8));
} else
{var statearr_11438_11460 = state_11434__$1;(statearr_11438_11460[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11435 === (1)))
{var inst_11405 = cljs.core.vec.call(null,chs);var inst_11406 = inst_11405;var state_11434__$1 = (function (){var statearr_11439 = state_11434;(statearr_11439[(10)] = inst_11406);
return statearr_11439;
})();var statearr_11440_11461 = state_11434__$1;(statearr_11440_11461[(2)] = null);
(statearr_11440_11461[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11435 === (4)))
{var inst_11406 = (state_11434[(10)]);var state_11434__$1 = state_11434;return cljs.core.async.impl.ioc_helpers.ioc_alts_BANG_.call(null,state_11434__$1,(7),inst_11406);
} else
{if((state_val_11435 === (6)))
{var inst_11430 = (state_11434[(2)]);var state_11434__$1 = state_11434;var statearr_11441_11462 = state_11434__$1;(statearr_11441_11462[(2)] = inst_11430);
(statearr_11441_11462[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11435 === (3)))
{var inst_11432 = (state_11434[(2)]);var state_11434__$1 = state_11434;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_11434__$1,inst_11432);
} else
{if((state_val_11435 === (2)))
{var inst_11406 = (state_11434[(10)]);var inst_11408 = cljs.core.count.call(null,inst_11406);var inst_11409 = (inst_11408 > (0));var state_11434__$1 = state_11434;if(cljs.core.truth_(inst_11409))
{var statearr_11443_11463 = state_11434__$1;(statearr_11443_11463[(1)] = (4));
} else
{var statearr_11444_11464 = state_11434__$1;(statearr_11444_11464[(1)] = (5));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11435 === (11)))
{var inst_11406 = (state_11434[(10)]);var inst_11423 = (state_11434[(2)]);var tmp11442 = inst_11406;var inst_11406__$1 = tmp11442;var state_11434__$1 = (function (){var statearr_11445 = state_11434;(statearr_11445[(10)] = inst_11406__$1);
(statearr_11445[(11)] = inst_11423);
return statearr_11445;
})();var statearr_11446_11465 = state_11434__$1;(statearr_11446_11465[(2)] = null);
(statearr_11446_11465[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11435 === (9)))
{var inst_11414 = (state_11434[(8)]);var state_11434__$1 = state_11434;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11434__$1,(11),out,inst_11414);
} else
{if((state_val_11435 === (5)))
{var inst_11428 = cljs.core.async.close_BANG_.call(null,out);var state_11434__$1 = state_11434;var statearr_11447_11466 = state_11434__$1;(statearr_11447_11466[(2)] = inst_11428);
(statearr_11447_11466[(1)] = (6));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11435 === (10)))
{var inst_11426 = (state_11434[(2)]);var state_11434__$1 = state_11434;var statearr_11448_11467 = state_11434__$1;(statearr_11448_11467[(2)] = inst_11426);
(statearr_11448_11467[(1)] = (6));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11435 === (8)))
{var inst_11406 = (state_11434[(10)]);var inst_11413 = (state_11434[(7)]);var inst_11415 = (state_11434[(9)]);var inst_11414 = (state_11434[(8)]);var inst_11418 = (function (){var c = inst_11415;var v = inst_11414;var vec__11411 = inst_11413;var cs = inst_11406;return ((function (c,v,vec__11411,cs,inst_11406,inst_11413,inst_11415,inst_11414,state_val_11435,c__6353__auto___11458,out){
return (function (p1__11351_SHARP_){return cljs.core.not_EQ_.call(null,c,p1__11351_SHARP_);
});
;})(c,v,vec__11411,cs,inst_11406,inst_11413,inst_11415,inst_11414,state_val_11435,c__6353__auto___11458,out))
})();var inst_11419 = cljs.core.filterv.call(null,inst_11418,inst_11406);var inst_11406__$1 = inst_11419;var state_11434__$1 = (function (){var statearr_11449 = state_11434;(statearr_11449[(10)] = inst_11406__$1);
return statearr_11449;
})();var statearr_11450_11468 = state_11434__$1;(statearr_11450_11468[(2)] = null);
(statearr_11450_11468[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___11458,out))
;return ((function (switch__6338__auto__,c__6353__auto___11458,out){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_11454 = [null,null,null,null,null,null,null,null,null,null,null,null];(statearr_11454[(0)] = state_machine__6339__auto__);
(statearr_11454[(1)] = (1));
return statearr_11454;
});
var state_machine__6339__auto____1 = (function (state_11434){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_11434);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e11455){if((e11455 instanceof Object))
{var ex__6342__auto__ = e11455;var statearr_11456_11469 = state_11434;(statearr_11456_11469[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11434);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e11455;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__11470 = state_11434;
state_11434 = G__11470;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_11434){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_11434);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___11458,out))
})();var state__6355__auto__ = (function (){var statearr_11457 = f__6354__auto__.call(null);(statearr_11457[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___11458);
return statearr_11457;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___11458,out))
);
return out;
});
merge = function(chs,buf_or_n){
switch(arguments.length){
case 1:
return merge__1.call(this,chs);
case 2:
return merge__2.call(this,chs,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
merge.cljs$core$IFn$_invoke$arity$1 = merge__1;
merge.cljs$core$IFn$_invoke$arity$2 = merge__2;
return merge;
})()
;
/**
* Returns a channel containing the single (collection) result of the
* items taken from the channel conjoined to the supplied
* collection. ch must close before into produces a result.
*/
cljs.core.async.into = (function into(coll,ch){return cljs.core.async.reduce.call(null,cljs.core.conj,coll,ch);
});
/**
* Returns a channel that will return, at most, n items from ch. After n items
* have been returned, or ch has been closed, the return chanel will close.
* 
* The output channel is unbuffered by default, unless buf-or-n is given.
*/
cljs.core.async.take = (function() {
var take = null;
var take__2 = (function (n,ch){return take.call(null,n,ch,null);
});
var take__3 = (function (n,ch,buf_or_n){var out = cljs.core.async.chan.call(null,buf_or_n);var c__6353__auto___11563 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___11563,out){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___11563,out){
return (function (state_11540){var state_val_11541 = (state_11540[(1)]);if((state_val_11541 === (7)))
{var inst_11522 = (state_11540[(7)]);var inst_11522__$1 = (state_11540[(2)]);var inst_11523 = (inst_11522__$1 == null);var inst_11524 = cljs.core.not.call(null,inst_11523);var state_11540__$1 = (function (){var statearr_11542 = state_11540;(statearr_11542[(7)] = inst_11522__$1);
return statearr_11542;
})();if(inst_11524)
{var statearr_11543_11564 = state_11540__$1;(statearr_11543_11564[(1)] = (8));
} else
{var statearr_11544_11565 = state_11540__$1;(statearr_11544_11565[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (1)))
{var inst_11517 = (0);var state_11540__$1 = (function (){var statearr_11545 = state_11540;(statearr_11545[(8)] = inst_11517);
return statearr_11545;
})();var statearr_11546_11566 = state_11540__$1;(statearr_11546_11566[(2)] = null);
(statearr_11546_11566[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (4)))
{var state_11540__$1 = state_11540;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_11540__$1,(7),ch);
} else
{if((state_val_11541 === (6)))
{var inst_11535 = (state_11540[(2)]);var state_11540__$1 = state_11540;var statearr_11547_11567 = state_11540__$1;(statearr_11547_11567[(2)] = inst_11535);
(statearr_11547_11567[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (3)))
{var inst_11537 = (state_11540[(2)]);var inst_11538 = cljs.core.async.close_BANG_.call(null,out);var state_11540__$1 = (function (){var statearr_11548 = state_11540;(statearr_11548[(9)] = inst_11537);
return statearr_11548;
})();return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_11540__$1,inst_11538);
} else
{if((state_val_11541 === (2)))
{var inst_11517 = (state_11540[(8)]);var inst_11519 = (inst_11517 < n);var state_11540__$1 = state_11540;if(cljs.core.truth_(inst_11519))
{var statearr_11549_11568 = state_11540__$1;(statearr_11549_11568[(1)] = (4));
} else
{var statearr_11550_11569 = state_11540__$1;(statearr_11550_11569[(1)] = (5));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (11)))
{var inst_11517 = (state_11540[(8)]);var inst_11527 = (state_11540[(2)]);var inst_11528 = (inst_11517 + (1));var inst_11517__$1 = inst_11528;var state_11540__$1 = (function (){var statearr_11551 = state_11540;(statearr_11551[(10)] = inst_11527);
(statearr_11551[(8)] = inst_11517__$1);
return statearr_11551;
})();var statearr_11552_11570 = state_11540__$1;(statearr_11552_11570[(2)] = null);
(statearr_11552_11570[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (9)))
{var state_11540__$1 = state_11540;var statearr_11553_11571 = state_11540__$1;(statearr_11553_11571[(2)] = null);
(statearr_11553_11571[(1)] = (10));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (5)))
{var state_11540__$1 = state_11540;var statearr_11554_11572 = state_11540__$1;(statearr_11554_11572[(2)] = null);
(statearr_11554_11572[(1)] = (6));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (10)))
{var inst_11532 = (state_11540[(2)]);var state_11540__$1 = state_11540;var statearr_11555_11573 = state_11540__$1;(statearr_11555_11573[(2)] = inst_11532);
(statearr_11555_11573[(1)] = (6));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11541 === (8)))
{var inst_11522 = (state_11540[(7)]);var state_11540__$1 = state_11540;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11540__$1,(11),out,inst_11522);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___11563,out))
;return ((function (switch__6338__auto__,c__6353__auto___11563,out){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_11559 = [null,null,null,null,null,null,null,null,null,null,null];(statearr_11559[(0)] = state_machine__6339__auto__);
(statearr_11559[(1)] = (1));
return statearr_11559;
});
var state_machine__6339__auto____1 = (function (state_11540){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_11540);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e11560){if((e11560 instanceof Object))
{var ex__6342__auto__ = e11560;var statearr_11561_11574 = state_11540;(statearr_11561_11574[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11540);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e11560;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__11575 = state_11540;
state_11540 = G__11575;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_11540){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_11540);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___11563,out))
})();var state__6355__auto__ = (function (){var statearr_11562 = f__6354__auto__.call(null);(statearr_11562[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___11563);
return statearr_11562;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___11563,out))
);
return out;
});
take = function(n,ch,buf_or_n){
switch(arguments.length){
case 2:
return take__2.call(this,n,ch);
case 3:
return take__3.call(this,n,ch,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
take.cljs$core$IFn$_invoke$arity$2 = take__2;
take.cljs$core$IFn$_invoke$arity$3 = take__3;
return take;
})()
;
/**
* Returns a channel that will contain values from ch. Consecutive duplicate
* values will be dropped.
* 
* The output channel is unbuffered by default, unless buf-or-n is given.
*/
cljs.core.async.unique = (function() {
var unique = null;
var unique__1 = (function (ch){return unique.call(null,ch,null);
});
var unique__2 = (function (ch,buf_or_n){var out = cljs.core.async.chan.call(null,buf_or_n);var c__6353__auto___11672 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___11672,out){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___11672,out){
return (function (state_11647){var state_val_11648 = (state_11647[(1)]);if((state_val_11648 === (7)))
{var inst_11642 = (state_11647[(2)]);var state_11647__$1 = state_11647;var statearr_11649_11673 = state_11647__$1;(statearr_11649_11673[(2)] = inst_11642);
(statearr_11649_11673[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11648 === (1)))
{var inst_11624 = null;var state_11647__$1 = (function (){var statearr_11650 = state_11647;(statearr_11650[(7)] = inst_11624);
return statearr_11650;
})();var statearr_11651_11674 = state_11647__$1;(statearr_11651_11674[(2)] = null);
(statearr_11651_11674[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11648 === (4)))
{var inst_11627 = (state_11647[(8)]);var inst_11627__$1 = (state_11647[(2)]);var inst_11628 = (inst_11627__$1 == null);var inst_11629 = cljs.core.not.call(null,inst_11628);var state_11647__$1 = (function (){var statearr_11652 = state_11647;(statearr_11652[(8)] = inst_11627__$1);
return statearr_11652;
})();if(inst_11629)
{var statearr_11653_11675 = state_11647__$1;(statearr_11653_11675[(1)] = (5));
} else
{var statearr_11654_11676 = state_11647__$1;(statearr_11654_11676[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11648 === (6)))
{var state_11647__$1 = state_11647;var statearr_11655_11677 = state_11647__$1;(statearr_11655_11677[(2)] = null);
(statearr_11655_11677[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11648 === (3)))
{var inst_11644 = (state_11647[(2)]);var inst_11645 = cljs.core.async.close_BANG_.call(null,out);var state_11647__$1 = (function (){var statearr_11656 = state_11647;(statearr_11656[(9)] = inst_11644);
return statearr_11656;
})();return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_11647__$1,inst_11645);
} else
{if((state_val_11648 === (2)))
{var state_11647__$1 = state_11647;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_11647__$1,(4),ch);
} else
{if((state_val_11648 === (11)))
{var inst_11627 = (state_11647[(8)]);var inst_11636 = (state_11647[(2)]);var inst_11624 = inst_11627;var state_11647__$1 = (function (){var statearr_11657 = state_11647;(statearr_11657[(7)] = inst_11624);
(statearr_11657[(10)] = inst_11636);
return statearr_11657;
})();var statearr_11658_11678 = state_11647__$1;(statearr_11658_11678[(2)] = null);
(statearr_11658_11678[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11648 === (9)))
{var inst_11627 = (state_11647[(8)]);var state_11647__$1 = state_11647;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11647__$1,(11),out,inst_11627);
} else
{if((state_val_11648 === (5)))
{var inst_11624 = (state_11647[(7)]);var inst_11627 = (state_11647[(8)]);var inst_11631 = cljs.core._EQ_.call(null,inst_11627,inst_11624);var state_11647__$1 = state_11647;if(inst_11631)
{var statearr_11660_11679 = state_11647__$1;(statearr_11660_11679[(1)] = (8));
} else
{var statearr_11661_11680 = state_11647__$1;(statearr_11661_11680[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11648 === (10)))
{var inst_11639 = (state_11647[(2)]);var state_11647__$1 = state_11647;var statearr_11662_11681 = state_11647__$1;(statearr_11662_11681[(2)] = inst_11639);
(statearr_11662_11681[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11648 === (8)))
{var inst_11624 = (state_11647[(7)]);var tmp11659 = inst_11624;var inst_11624__$1 = tmp11659;var state_11647__$1 = (function (){var statearr_11663 = state_11647;(statearr_11663[(7)] = inst_11624__$1);
return statearr_11663;
})();var statearr_11664_11682 = state_11647__$1;(statearr_11664_11682[(2)] = null);
(statearr_11664_11682[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___11672,out))
;return ((function (switch__6338__auto__,c__6353__auto___11672,out){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_11668 = [null,null,null,null,null,null,null,null,null,null,null];(statearr_11668[(0)] = state_machine__6339__auto__);
(statearr_11668[(1)] = (1));
return statearr_11668;
});
var state_machine__6339__auto____1 = (function (state_11647){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_11647);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e11669){if((e11669 instanceof Object))
{var ex__6342__auto__ = e11669;var statearr_11670_11683 = state_11647;(statearr_11670_11683[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11647);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e11669;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__11684 = state_11647;
state_11647 = G__11684;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_11647){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_11647);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___11672,out))
})();var state__6355__auto__ = (function (){var statearr_11671 = f__6354__auto__.call(null);(statearr_11671[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___11672);
return statearr_11671;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___11672,out))
);
return out;
});
unique = function(ch,buf_or_n){
switch(arguments.length){
case 1:
return unique__1.call(this,ch);
case 2:
return unique__2.call(this,ch,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
unique.cljs$core$IFn$_invoke$arity$1 = unique__1;
unique.cljs$core$IFn$_invoke$arity$2 = unique__2;
return unique;
})()
;
/**
* Returns a channel that will contain vectors of n items taken from ch. The
* final vector in the return channel may be smaller than n if ch closed before
* the vector could be completely filled.
* 
* The output channel is unbuffered by default, unless buf-or-n is given
*/
cljs.core.async.partition = (function() {
var partition = null;
var partition__2 = (function (n,ch){return partition.call(null,n,ch,null);
});
var partition__3 = (function (n,ch,buf_or_n){var out = cljs.core.async.chan.call(null,buf_or_n);var c__6353__auto___11819 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___11819,out){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___11819,out){
return (function (state_11789){var state_val_11790 = (state_11789[(1)]);if((state_val_11790 === (7)))
{var inst_11785 = (state_11789[(2)]);var state_11789__$1 = state_11789;var statearr_11791_11820 = state_11789__$1;(statearr_11791_11820[(2)] = inst_11785);
(statearr_11791_11820[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (1)))
{var inst_11752 = (new Array(n));var inst_11753 = inst_11752;var inst_11754 = (0);var state_11789__$1 = (function (){var statearr_11792 = state_11789;(statearr_11792[(7)] = inst_11753);
(statearr_11792[(8)] = inst_11754);
return statearr_11792;
})();var statearr_11793_11821 = state_11789__$1;(statearr_11793_11821[(2)] = null);
(statearr_11793_11821[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (4)))
{var inst_11757 = (state_11789[(9)]);var inst_11757__$1 = (state_11789[(2)]);var inst_11758 = (inst_11757__$1 == null);var inst_11759 = cljs.core.not.call(null,inst_11758);var state_11789__$1 = (function (){var statearr_11794 = state_11789;(statearr_11794[(9)] = inst_11757__$1);
return statearr_11794;
})();if(inst_11759)
{var statearr_11795_11822 = state_11789__$1;(statearr_11795_11822[(1)] = (5));
} else
{var statearr_11796_11823 = state_11789__$1;(statearr_11796_11823[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (15)))
{var inst_11779 = (state_11789[(2)]);var state_11789__$1 = state_11789;var statearr_11797_11824 = state_11789__$1;(statearr_11797_11824[(2)] = inst_11779);
(statearr_11797_11824[(1)] = (14));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (13)))
{var state_11789__$1 = state_11789;var statearr_11798_11825 = state_11789__$1;(statearr_11798_11825[(2)] = null);
(statearr_11798_11825[(1)] = (14));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (6)))
{var inst_11754 = (state_11789[(8)]);var inst_11775 = (inst_11754 > (0));var state_11789__$1 = state_11789;if(cljs.core.truth_(inst_11775))
{var statearr_11799_11826 = state_11789__$1;(statearr_11799_11826[(1)] = (12));
} else
{var statearr_11800_11827 = state_11789__$1;(statearr_11800_11827[(1)] = (13));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (3)))
{var inst_11787 = (state_11789[(2)]);var state_11789__$1 = state_11789;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_11789__$1,inst_11787);
} else
{if((state_val_11790 === (12)))
{var inst_11753 = (state_11789[(7)]);var inst_11777 = cljs.core.vec.call(null,inst_11753);var state_11789__$1 = state_11789;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11789__$1,(15),out,inst_11777);
} else
{if((state_val_11790 === (2)))
{var state_11789__$1 = state_11789;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_11789__$1,(4),ch);
} else
{if((state_val_11790 === (11)))
{var inst_11769 = (state_11789[(2)]);var inst_11770 = (new Array(n));var inst_11753 = inst_11770;var inst_11754 = (0);var state_11789__$1 = (function (){var statearr_11801 = state_11789;(statearr_11801[(7)] = inst_11753);
(statearr_11801[(10)] = inst_11769);
(statearr_11801[(8)] = inst_11754);
return statearr_11801;
})();var statearr_11802_11828 = state_11789__$1;(statearr_11802_11828[(2)] = null);
(statearr_11802_11828[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (9)))
{var inst_11753 = (state_11789[(7)]);var inst_11767 = cljs.core.vec.call(null,inst_11753);var state_11789__$1 = state_11789;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11789__$1,(11),out,inst_11767);
} else
{if((state_val_11790 === (5)))
{var inst_11762 = (state_11789[(11)]);var inst_11757 = (state_11789[(9)]);var inst_11753 = (state_11789[(7)]);var inst_11754 = (state_11789[(8)]);var inst_11761 = (inst_11753[inst_11754] = inst_11757);var inst_11762__$1 = (inst_11754 + (1));var inst_11763 = (inst_11762__$1 < n);var state_11789__$1 = (function (){var statearr_11803 = state_11789;(statearr_11803[(11)] = inst_11762__$1);
(statearr_11803[(12)] = inst_11761);
return statearr_11803;
})();if(cljs.core.truth_(inst_11763))
{var statearr_11804_11829 = state_11789__$1;(statearr_11804_11829[(1)] = (8));
} else
{var statearr_11805_11830 = state_11789__$1;(statearr_11805_11830[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (14)))
{var inst_11782 = (state_11789[(2)]);var inst_11783 = cljs.core.async.close_BANG_.call(null,out);var state_11789__$1 = (function (){var statearr_11807 = state_11789;(statearr_11807[(13)] = inst_11782);
return statearr_11807;
})();var statearr_11808_11831 = state_11789__$1;(statearr_11808_11831[(2)] = inst_11783);
(statearr_11808_11831[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (10)))
{var inst_11773 = (state_11789[(2)]);var state_11789__$1 = state_11789;var statearr_11809_11832 = state_11789__$1;(statearr_11809_11832[(2)] = inst_11773);
(statearr_11809_11832[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11790 === (8)))
{var inst_11762 = (state_11789[(11)]);var inst_11753 = (state_11789[(7)]);var tmp11806 = inst_11753;var inst_11753__$1 = tmp11806;var inst_11754 = inst_11762;var state_11789__$1 = (function (){var statearr_11810 = state_11789;(statearr_11810[(7)] = inst_11753__$1);
(statearr_11810[(8)] = inst_11754);
return statearr_11810;
})();var statearr_11811_11833 = state_11789__$1;(statearr_11811_11833[(2)] = null);
(statearr_11811_11833[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___11819,out))
;return ((function (switch__6338__auto__,c__6353__auto___11819,out){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_11815 = [null,null,null,null,null,null,null,null,null,null,null,null,null,null];(statearr_11815[(0)] = state_machine__6339__auto__);
(statearr_11815[(1)] = (1));
return statearr_11815;
});
var state_machine__6339__auto____1 = (function (state_11789){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_11789);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e11816){if((e11816 instanceof Object))
{var ex__6342__auto__ = e11816;var statearr_11817_11834 = state_11789;(statearr_11817_11834[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11789);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e11816;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__11835 = state_11789;
state_11789 = G__11835;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_11789){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_11789);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___11819,out))
})();var state__6355__auto__ = (function (){var statearr_11818 = f__6354__auto__.call(null);(statearr_11818[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___11819);
return statearr_11818;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___11819,out))
);
return out;
});
partition = function(n,ch,buf_or_n){
switch(arguments.length){
case 2:
return partition__2.call(this,n,ch);
case 3:
return partition__3.call(this,n,ch,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
partition.cljs$core$IFn$_invoke$arity$2 = partition__2;
partition.cljs$core$IFn$_invoke$arity$3 = partition__3;
return partition;
})()
;
/**
* Returns a channel that will contain vectors of items taken from ch. New
* vectors will be created whenever (f itm) returns a value that differs from
* the previous item's (f itm).
* 
* The output channel is unbuffered, unless buf-or-n is given
*/
cljs.core.async.partition_by = (function() {
var partition_by = null;
var partition_by__2 = (function (f,ch){return partition_by.call(null,f,ch,null);
});
var partition_by__3 = (function (f,ch,buf_or_n){var out = cljs.core.async.chan.call(null,buf_or_n);var c__6353__auto___11978 = cljs.core.async.chan.call(null,(1));cljs.core.async.impl.dispatch.run.call(null,((function (c__6353__auto___11978,out){
return (function (){var f__6354__auto__ = (function (){var switch__6338__auto__ = ((function (c__6353__auto___11978,out){
return (function (state_11948){var state_val_11949 = (state_11948[(1)]);if((state_val_11949 === (7)))
{var inst_11944 = (state_11948[(2)]);var state_11948__$1 = state_11948;var statearr_11950_11979 = state_11948__$1;(statearr_11950_11979[(2)] = inst_11944);
(statearr_11950_11979[(1)] = (3));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (1)))
{var inst_11907 = [];var inst_11908 = inst_11907;var inst_11909 = new cljs.core.Keyword("cljs.core.async","nothing","cljs.core.async/nothing",-69252123);var state_11948__$1 = (function (){var statearr_11951 = state_11948;(statearr_11951[(7)] = inst_11909);
(statearr_11951[(8)] = inst_11908);
return statearr_11951;
})();var statearr_11952_11980 = state_11948__$1;(statearr_11952_11980[(2)] = null);
(statearr_11952_11980[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (4)))
{var inst_11912 = (state_11948[(9)]);var inst_11912__$1 = (state_11948[(2)]);var inst_11913 = (inst_11912__$1 == null);var inst_11914 = cljs.core.not.call(null,inst_11913);var state_11948__$1 = (function (){var statearr_11953 = state_11948;(statearr_11953[(9)] = inst_11912__$1);
return statearr_11953;
})();if(inst_11914)
{var statearr_11954_11981 = state_11948__$1;(statearr_11954_11981[(1)] = (5));
} else
{var statearr_11955_11982 = state_11948__$1;(statearr_11955_11982[(1)] = (6));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (15)))
{var inst_11938 = (state_11948[(2)]);var state_11948__$1 = state_11948;var statearr_11956_11983 = state_11948__$1;(statearr_11956_11983[(2)] = inst_11938);
(statearr_11956_11983[(1)] = (14));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (13)))
{var state_11948__$1 = state_11948;var statearr_11957_11984 = state_11948__$1;(statearr_11957_11984[(2)] = null);
(statearr_11957_11984[(1)] = (14));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (6)))
{var inst_11908 = (state_11948[(8)]);var inst_11933 = inst_11908.length;var inst_11934 = (inst_11933 > (0));var state_11948__$1 = state_11948;if(cljs.core.truth_(inst_11934))
{var statearr_11958_11985 = state_11948__$1;(statearr_11958_11985[(1)] = (12));
} else
{var statearr_11959_11986 = state_11948__$1;(statearr_11959_11986[(1)] = (13));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (3)))
{var inst_11946 = (state_11948[(2)]);var state_11948__$1 = state_11948;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_11948__$1,inst_11946);
} else
{if((state_val_11949 === (12)))
{var inst_11908 = (state_11948[(8)]);var inst_11936 = cljs.core.vec.call(null,inst_11908);var state_11948__$1 = state_11948;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11948__$1,(15),out,inst_11936);
} else
{if((state_val_11949 === (2)))
{var state_11948__$1 = state_11948;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_11948__$1,(4),ch);
} else
{if((state_val_11949 === (11)))
{var inst_11912 = (state_11948[(9)]);var inst_11916 = (state_11948[(10)]);var inst_11926 = (state_11948[(2)]);var inst_11927 = [];var inst_11928 = inst_11927.push(inst_11912);var inst_11908 = inst_11927;var inst_11909 = inst_11916;var state_11948__$1 = (function (){var statearr_11960 = state_11948;(statearr_11960[(7)] = inst_11909);
(statearr_11960[(11)] = inst_11928);
(statearr_11960[(8)] = inst_11908);
(statearr_11960[(12)] = inst_11926);
return statearr_11960;
})();var statearr_11961_11987 = state_11948__$1;(statearr_11961_11987[(2)] = null);
(statearr_11961_11987[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (9)))
{var inst_11908 = (state_11948[(8)]);var inst_11924 = cljs.core.vec.call(null,inst_11908);var state_11948__$1 = state_11948;return cljs.core.async.impl.ioc_helpers.put_BANG_.call(null,state_11948__$1,(11),out,inst_11924);
} else
{if((state_val_11949 === (5)))
{var inst_11909 = (state_11948[(7)]);var inst_11912 = (state_11948[(9)]);var inst_11916 = (state_11948[(10)]);var inst_11916__$1 = f.call(null,inst_11912);var inst_11917 = cljs.core._EQ_.call(null,inst_11916__$1,inst_11909);var inst_11918 = cljs.core.keyword_identical_QMARK_.call(null,inst_11909,new cljs.core.Keyword("cljs.core.async","nothing","cljs.core.async/nothing",-69252123));var inst_11919 = (inst_11917) || (inst_11918);var state_11948__$1 = (function (){var statearr_11962 = state_11948;(statearr_11962[(10)] = inst_11916__$1);
return statearr_11962;
})();if(cljs.core.truth_(inst_11919))
{var statearr_11963_11988 = state_11948__$1;(statearr_11963_11988[(1)] = (8));
} else
{var statearr_11964_11989 = state_11948__$1;(statearr_11964_11989[(1)] = (9));
}
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (14)))
{var inst_11941 = (state_11948[(2)]);var inst_11942 = cljs.core.async.close_BANG_.call(null,out);var state_11948__$1 = (function (){var statearr_11966 = state_11948;(statearr_11966[(13)] = inst_11941);
return statearr_11966;
})();var statearr_11967_11990 = state_11948__$1;(statearr_11967_11990[(2)] = inst_11942);
(statearr_11967_11990[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (10)))
{var inst_11931 = (state_11948[(2)]);var state_11948__$1 = state_11948;var statearr_11968_11991 = state_11948__$1;(statearr_11968_11991[(2)] = inst_11931);
(statearr_11968_11991[(1)] = (7));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{if((state_val_11949 === (8)))
{var inst_11912 = (state_11948[(9)]);var inst_11916 = (state_11948[(10)]);var inst_11908 = (state_11948[(8)]);var inst_11921 = inst_11908.push(inst_11912);var tmp11965 = inst_11908;var inst_11908__$1 = tmp11965;var inst_11909 = inst_11916;var state_11948__$1 = (function (){var statearr_11969 = state_11948;(statearr_11969[(7)] = inst_11909);
(statearr_11969[(8)] = inst_11908__$1);
(statearr_11969[(14)] = inst_11921);
return statearr_11969;
})();var statearr_11970_11992 = state_11948__$1;(statearr_11970_11992[(2)] = null);
(statearr_11970_11992[(1)] = (2));
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{return null;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
});})(c__6353__auto___11978,out))
;return ((function (switch__6338__auto__,c__6353__auto___11978,out){
return (function() {
var state_machine__6339__auto__ = null;
var state_machine__6339__auto____0 = (function (){var statearr_11974 = [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null];(statearr_11974[(0)] = state_machine__6339__auto__);
(statearr_11974[(1)] = (1));
return statearr_11974;
});
var state_machine__6339__auto____1 = (function (state_11948){while(true){
var ret_value__6340__auto__ = (function (){try{while(true){
var result__6341__auto__ = switch__6338__auto__.call(null,state_11948);if(cljs.core.keyword_identical_QMARK_.call(null,result__6341__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
continue;
}
} else
{return result__6341__auto__;
}
break;
}
}catch (e11975){if((e11975 instanceof Object))
{var ex__6342__auto__ = e11975;var statearr_11976_11993 = state_11948;(statearr_11976_11993[(5)] = ex__6342__auto__);
cljs.core.async.impl.ioc_helpers.process_exception.call(null,state_11948);
return new cljs.core.Keyword(null,"recur","recur",-437573268);
} else
{throw e11975;

}
}})();if(cljs.core.keyword_identical_QMARK_.call(null,ret_value__6340__auto__,new cljs.core.Keyword(null,"recur","recur",-437573268)))
{{
var G__11994 = state_11948;
state_11948 = G__11994;
continue;
}
} else
{return ret_value__6340__auto__;
}
break;
}
});
state_machine__6339__auto__ = function(state_11948){
switch(arguments.length){
case 0:
return state_machine__6339__auto____0.call(this);
case 1:
return state_machine__6339__auto____1.call(this,state_11948);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__6339__auto____0;
state_machine__6339__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__6339__auto____1;
return state_machine__6339__auto__;
})()
;})(switch__6338__auto__,c__6353__auto___11978,out))
})();var state__6355__auto__ = (function (){var statearr_11977 = f__6354__auto__.call(null);(statearr_11977[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__6353__auto___11978);
return statearr_11977;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__6355__auto__);
});})(c__6353__auto___11978,out))
);
return out;
});
partition_by = function(f,ch,buf_or_n){
switch(arguments.length){
case 2:
return partition_by__2.call(this,f,ch);
case 3:
return partition_by__3.call(this,f,ch,buf_or_n);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
partition_by.cljs$core$IFn$_invoke$arity$2 = partition_by__2;
partition_by.cljs$core$IFn$_invoke$arity$3 = partition_by__3;
return partition_by;
})()
;

//# sourceMappingURL=async.js.map