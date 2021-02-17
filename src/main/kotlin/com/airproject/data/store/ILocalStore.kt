package com.airproject.data.store

import com.airproject.data.store.airport.AirportPojo

interface ILocalStore<T> {

  val content: List<AirportPojo>
}
