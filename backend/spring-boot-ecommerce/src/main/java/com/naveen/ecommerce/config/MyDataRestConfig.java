package com.naveen.ecommerce.config;

import com.naveen.ecommerce.entity.Country;
import com.naveen.ecommerce.entity.Product;
import com.naveen.ecommerce.entity.ProductCategory;
import com.naveen.ecommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    EntityManager entityManager;

    /// Injecting entityManager
    @Autowired
    MyDataRestConfig(EntityManager theEntityManager){
        entityManager=theEntityManager;
    }



    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        disableHttpMethods(Product.class,config,theUnsupportedActions);

        // disable HTTP methods for Product: PUT, POST and DELETE
              disableHttpMethods(Product.class,config,theUnsupportedActions);
        disableHttpMethods(ProductCategory.class,config,theUnsupportedActions);
        disableHttpMethods(Country.class,config,theUnsupportedActions);
        disableHttpMethods(State.class,config,theUnsupportedActions);




//        config.getExposureConfiguration()

//                .forDomainType(Product.class)
//                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
//                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
//

//        // disable HTTP methods for ProductCategory: PUT, POST and DELETE
//        config.getExposureConfiguration()
//                .forDomainType(ProductCategory.class)
//                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
//                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));


        // calling an internal helper method to expose category Id to display
        // category dynamically
        exposeIds(config);





    }

    private void disableHttpMethods(Class theClass,RepositoryRestConfiguration config,HttpMethod[] theUnsupportedActions ){
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

    }
    private void exposeIds(RepositoryRestConfiguration config) {
        // expose entity ids

// - gets a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();


        // - create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();


        // - get the entity types for the entities
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }


// - expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}
