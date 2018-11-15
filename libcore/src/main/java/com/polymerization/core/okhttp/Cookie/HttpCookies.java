package com.polymerization.core.okhttp.Cookie;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import okhttp3.Cookie;

/**
 * Author: yangweichao
 * Date:   2018/11/14 11:32 AM
 * Description:
 */


public final class HttpCookies implements Serializable {
    private transient final Cookie cookies;
    private transient Cookie clientCookies;

    public HttpCookies(@NonNull Cookie cookies) {
        this.cookies = cookies;
    }

    // FIXME: 2018/11/14  这里clientCookie 需要优化，貌似永远为null
    public Cookie getCookies() {
        Cookie bestCookies = cookies;
        if (clientCookies != null) {
            bestCookies = clientCookies;
        }
        return bestCookies;
    }

    private void writeObject(@NonNull ObjectOutputStream out) throws IOException {
        out.writeObject(cookies.name());
        out.writeObject(cookies.value());
        out.writeLong(cookies.expiresAt());
        out.writeObject(cookies.domain());
        out.writeObject(cookies.path());
        out.writeBoolean(cookies.secure());
        out.writeBoolean(cookies.httpOnly());
        out.writeBoolean(cookies.hostOnly());
        out.writeBoolean(cookies.persistent());
    }

    private void readObject(@NonNull ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        long expiresAt = in.readLong();
        String domain = (String) in.readObject();
        String path = (String) in.readObject();
        boolean secure = in.readBoolean();
        boolean httpOnly = in.readBoolean();
        boolean hostOnly = in.readBoolean();
        boolean persistent = in.readBoolean();
        Cookie.Builder builder = new Cookie.Builder();
        builder = builder
                .name(name)
                .value(value)
                .expiresAt(expiresAt)
                .path(path);
        builder = hostOnly ? builder.hostOnlyDomain(domain) : builder.domain(domain);
        builder = secure ? builder.secure() : builder;
        builder = httpOnly ? builder.httpOnly() : builder;
        clientCookies = builder.build();
    }
}
