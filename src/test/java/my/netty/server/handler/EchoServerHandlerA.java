package my.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoServerHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.fireChannelRead(msg);
        ByteBuf in = (ByteBuf) msg;
        System.out.println("EchoServerHandlerA receive: " + in.toString(CharsetUtil.UTF_8));        //2

        Channel channel = ctx.channel();
        ByteBufAllocator alloc = channel.alloc();
        ByteBuf byteBuf = alloc.buffer().writeBytes("EchoServerHandlerA writeAndFlush!".getBytes());
        ctx.writeAndFlush(byteBuf);
    }
}
