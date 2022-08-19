package me.simonpojok.domain.common

import me.simonpojok.domain.common.usecase.CoroutineContextProvider
import kotlin.coroutines.CoroutineContext

private class FakeCoroutineContextProvider(
    override val main: CoroutineContext = fakeCoroutineContext,
    override val io: CoroutineContext = fakeCoroutineContext
) : CoroutineContextProvider

val fakeCoroutineContextProvider: CoroutineContextProvider =
    FakeCoroutineContextProvider()
