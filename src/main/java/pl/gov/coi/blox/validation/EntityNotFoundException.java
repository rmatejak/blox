package pl.gov.coi.blox.validation;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException{

        private final EntityNotFoundMassage entityNotFoundMassage;

        public EntityNotFoundException (EntityNotFoundMassage entityNotFoundMassage){
            super(entityNotFoundMassage.getMessage());
            this.entityNotFoundMassage = entityNotFoundMassage;
        }

    }
