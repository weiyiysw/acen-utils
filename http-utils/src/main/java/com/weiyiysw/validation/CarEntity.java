package com.weiyiysw.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yishiwei
 * @Date 2020/3/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @NotNull(message = "{car.manufacturer}")
    private String manufacturer;

    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;

    @Min(2)
    private int seatCount;
}
