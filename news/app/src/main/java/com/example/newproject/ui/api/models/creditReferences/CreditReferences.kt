package com.example.newproject.ui.api.models.creditReferences

data class CreditReferences(
    val areas: List<Area>,
    val cities: List<City>,
    val offices: List<Office>,
    val offlineCredit: OfflineCredit,
    val onlineCredit: OnlineCredit,
    val payOutTypes: List<PayOutType>,
    val providers: List<Provider>,
    val purposes: List<Purpose>,
    val regions: List<Region>,
    val relations: List<Relation>
)