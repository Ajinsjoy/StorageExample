package com.wac.readcsv.csvreader.data.data.mapper

import com.wac.readcsv.csvreader.data.data.local.entity.DetailsEntity
import com.wac.readcsv.csvreader.domain.data.Details

fun Details.toDetailsEntity(): DetailsEntity {
    return DetailsEntity(
        Industry_aggregation_NZSIOC = Industry_aggregation_NZSIOC,
        Industry_code_ANZSIC06 = Industry_code_ANZSIC06,
        Industry_code_NZSIOC = Industry_code_NZSIOC,
        Industry_name_NZSIOC = Industry_name_NZSIOC,
        Units = Units,
        Value = Value,
        Variable_category = Variable_category,
        Variable_code = Variable_code,
        Variable_name = Variable_name,
        Year = Year
    )
}


fun DetailsEntity.toDetails(): Details {
    return Details(
        Industry_aggregation_NZSIOC = Industry_aggregation_NZSIOC,
        Industry_code_ANZSIC06 = Industry_code_ANZSIC06,
        Industry_code_NZSIOC = Industry_code_NZSIOC,
        Industry_name_NZSIOC = Industry_name_NZSIOC,
        Units = Units,
        Value = Value,
        Variable_category = Variable_category,
        Variable_code = Variable_code,
        Variable_name = Variable_name,
        Year = Year
    )
}