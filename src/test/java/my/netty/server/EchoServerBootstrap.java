package my.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import my.netty.server.handler.EchoServerHandlerA;
import my.netty.server.handler.EchoServerHandlerB;
import my.netty.server.handler.EchoServerHandlerC;
import org.junit.Test;

import java.net.InetSocketAddress;

public class EchoServerBootstrap {

    @Test
    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4
                    .channel(NioServerSocketChannel.class)        //5
                    .localAddress(new InetSocketAddress(8080))    //6
                    .childHandler(new ChannelInitializer<SocketChannel>() { //7
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new EchoServerHandlerA());
                            ch.pipeline().addLast(new EchoServerHandlerB());
                            ch.pipeline().addLast(new EchoServerHandlerC());
                        }
                    });

            ChannelFuture f = b.bind().sync();            //8
            System.out.println(EchoServerBootstrap.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9
        } finally {
            group.shutdownGracefully().sync();            //10
        }
    }

}
