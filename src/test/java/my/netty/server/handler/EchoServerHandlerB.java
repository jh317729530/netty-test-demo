package my.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoServerHandlerB extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("EchoServerHandlerB: " + in.toString(CharsetUtil.UTF_8));        //2
//        ctx.write("EchoServerHandlerB write! \n".getBytes());
        Channel channel = ctx.channel();
        ByteBufAllocator alloc = channel.alloc();
        ByteBuf buffer = alloc.buffer();
        buffer.writeBytes("EchoServerHandlerB write! \n".getBytes());
//        channel.write(buffer);
    }
}
