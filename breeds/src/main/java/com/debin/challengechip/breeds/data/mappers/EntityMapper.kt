package com.debin.challengechip.breeds.data.mappers

abstract class EntityMapper<in E, out M> {
    abstract fun mapFromRemote(type : E) : M
}