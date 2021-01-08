package ru.he.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.he.controllers.BandController;
import ru.he.models.Band;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BandRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Band>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Band> process(EntityModel<Band> model) {
        Band band = model.getContent();
        if (band != null && band.getState().equals("Draft")) {
            model.add(linkTo(methodOn(BandController.class)
                    .publish(band.getId())).withRel("publish"));
        }

        if (band!= null && band.getState().equals("Published")) {
            model.add(links.linkToItemResource(Band.class, band.getId()).
                    withRel("delete"));
        }
        return model;
    }
}
