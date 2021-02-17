package com.airproject.store

import com.airproject.store.airport.AirportPojo

interface ILocalStore<T> {

  val content: List<AirportPojo>
}
