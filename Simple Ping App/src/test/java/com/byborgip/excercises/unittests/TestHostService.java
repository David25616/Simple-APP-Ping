package com.byborgip.excercises.unittests;

import com.byborgip.excercises.app.Application;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.services.HostService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestHostService {

    private HostService service = HostService.getInstance();

    @Before
    public void setup(){
        Application.APP = new Application(null);
    }

    @Test
    public void testAddHost(){
        Host host = new Host("www.google.com", 200, "www.test.com");
        host = service.addHost(host);
        Assert.assertEquals(1L, host.getId());
    }

    @Test
    public void testGetById(){
        Host host = new Host("www.google.com", 200, "www.test.com");
        host = service.addHost(host);
        final long hostId = host.getId();
        Assert.assertNotNull(host = service.getHostById(hostId));
        Assert.assertEquals(hostId, host.getId());
    }

    @Test
    public void testGetAll(){
        Host host = new Host("www.google.com", 200, "www.test.com");
        service.addHost(host);
        Host host2 = new Host("www.bing.com", 200, "www.test.com");
        service.addHost(host2);
        Host host3 = new Host("www.yahoo.com", 200, "www.test.com");
        service.addHost(host3);

        Assert.assertEquals(3,service.getAll().size());
    }

    @Test
    public void testRemove(){
        Host host = new Host("www.google.com", 200, "www.test.com");
        host = service.addHost(host);
        service.deleteHost(host);
        Assert.assertTrue(service.getAll().size() == 0);
    }
}
