package com.eharmony.aloha.dataset.vw

import com.eharmony.aloha.dataset.SparseCovariateProducer
import com.eharmony.aloha.dataset.json.SparseSpec
import com.eharmony.aloha.dataset.vw.json.VwJsonLike
import com.eharmony.aloha.semantics.compiled.CompiledSemantics

trait VwCovariateProducer[A] { self: SparseCovariateProducer =>

    /**
     * @param semantics semantics for function creation.
     * @param json JSON from which to derive feature definitions.
     * @return (covariates, default, namespaces, optional normalizer)
     */
    protected[this] def getVwData(semantics: CompiledSemantics[A], json: VwJsonLike) = {

        // Attempt to create the covariate data.  When no default is given, default to an empty sequence of KV Pairs.
        val covariates = getCovariates(semantics, json, SparseSpec.defVal)

        // Get the namespace information.
        val (default, nss) = json.namespaceIndices()

        // If we should normalize the feature, create get the proper normalizer.
        val normalizer = if (json.shouldNormalizeFeatures) Some(VwFeatureNormalizer.instance) else None

        (covariates, default, nss, normalizer)
    }
}
