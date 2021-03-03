package com.haouet.airproject.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Suppress("unused")   // Receiver is needed for type inference
inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)