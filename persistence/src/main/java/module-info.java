module persistence {
    exports jankowiak.kamil.persistence.model;
    exports jankowiak.kamil.persistence.repository.impl;
    exports jankowiak.kamil.persistence.enums;
    exports jankowiak.kamil.persistence.repository;

    requires gson;
    requires java.sql;

    opens jankowiak.kamil.persistence.model;

}