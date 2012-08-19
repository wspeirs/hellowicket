package com.hellowicket;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.page.IPageManagerContext;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.pageStore.memory.HttpSessionDataStore;
import org.apache.wicket.pageStore.memory.PageNumberEvictionStrategy;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.hellowicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<HomePage> getHomePage() {
	return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
	super.init();

	// remove thread monitoring from resource watcher
	this.getResourceSettings().setResourcePollFrequency(null);

	final IPageManagerContext pageManagerContext = this
		.getPageManagerContext();

	this.setPageManagerProvider(new DefaultPageManagerProvider(this) {
	    protected IDataStore newDataStore() {
		return new HttpSessionDataStore(pageManagerContext, new PageNumberEvictionStrategy(20));
	    }
	});
    }
}
