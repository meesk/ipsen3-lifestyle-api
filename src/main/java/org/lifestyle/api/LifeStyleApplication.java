/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api;

import org.lifestyle.api.resources.HelloResource;

import io.dropwizard.Application;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;

/**
 *
 * @author meesk
 */


public class LifeStyleApplication extends Application<LifeStyleConfiguration> {
    
    private ConfiguredBundle assetsBundle;
        
    public static void main(String[] args) throws Exception {
        new LifeStyleApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<LifeStyleConfiguration> bootstrap) {
        assetsBundle = (ConfiguredBundle) new ConfiguredAssetsBundle("/assets/", "/client", "index.html");
        bootstrap.addBundle(assetsBundle);
    }

    @Override
    public void run(LifeStyleConfiguration configuration,
                    Environment environment) {
    final HelloResource resource = new HelloResource(
    );
    environment.jersey().register(resource);
    }

}