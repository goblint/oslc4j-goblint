@OslcSchema({
        @OslcNamespaceDefinition(prefix = "dcterms"   , namespaceURI = "http://purl.org/dc/terms/"),
        @OslcNamespaceDefinition(prefix = "rdf"       , namespaceURI = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
        @OslcNamespaceDefinition(prefix = "rdfs"      , namespaceURI = "http://www.w3.org/2000/01/rdf-schema#"),
        @OslcNamespaceDefinition(prefix = "oslc"      , namespaceURI = "http://open- services.net/ns/core#"),
        @OslcNamespaceDefinition(prefix = "oslc-qm"   , namespaceURI = "http://open-services.net/ns/qm#"),
        @OslcNamespaceDefinition(prefix = "oslc-asset", namespaceURI = "http://open-services.net/ns/asset#"),
        @OslcNamespaceDefinition(prefix = "goblint"   , namespaceURI = Constants.GOBLINT_NAMESPACE)
})
package de.tum.in.goblint.oslc.resources;

import de.tum.in.goblint.oslc.Constants;
import org.eclipse.lyo.oslc4j.core.annotation.OslcNamespaceDefinition;
import org.eclipse.lyo.oslc4j.core.annotation.OslcSchema;

