package net.onurozcelik.ejbclient;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.onurozcelik.ejbsample.User;
import net.onurozcelik.ejbsample.UserBean;

public class RemoteEJBClient {
    public static void main(String[] args) throws Exception {
        UserBean bean = lookupUserEJB();
        User user = new User();
        user.setUsername("onur.ozcelik");
        bean.create(user);
        List<User> users = bean.findAll();
        if (users.size() > 0) {
            User aUser = users.get(0);
            System.out.println(aUser.getUsername());
        }
    }

    private static UserBean lookupUserEJB() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
        final Context context = new InitialContext(jndiProperties);
        return (UserBean) context.lookup("ejb:/ejbsample-1.0-SNAPSHOT/UserBeanImpl!net.onurozcelik.ejbsample.UserBean");
    }
}
