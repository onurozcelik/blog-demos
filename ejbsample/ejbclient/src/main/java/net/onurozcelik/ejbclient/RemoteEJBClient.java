package net.onurozcelik.ejbclient;

import net.onurozcelik.ejbserver.User;
import net.onurozcelik.ejbserver.UserBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;

public class RemoteEJBClient {

    public static void main(String[] args) throws Exception {
        UserBean bean = lookupUserEJB();
        User user = new User();
        user.setUsername("onur.ozcelik");
        bean.create(user);
        List<User> users = bean.findAll();
        if (!users.isEmpty()) {
            User aUser = users.get(0);
            System.out.println(aUser.getUsername());
        }
    }

    private static UserBean lookupUserEJB() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"remote+http://localhost:8081");
        final Context context = new InitialContext(jndiProperties);
        return (UserBean) context.lookup("ejb:/ejbserver-1.0-SNAPSHOT/UserBeanImpl!net.onurozcelik.ejbserver.UserBean");
    }
}
