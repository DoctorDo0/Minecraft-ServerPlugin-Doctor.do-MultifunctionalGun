package me.Doctor_do.multifunctionalgun.setup;

import me.Doctor_do.multifunctionalgun.items.materials_register.Advanced_Materials_Item_Register;
import me.Doctor_do.multifunctionalgun.items.materials_register.Basic_Materials_Item_Register;
import me.Doctor_do.multifunctionalgun.items.materials_register.Gun_And_Bullet_Item_Register;
import me.Doctor_do.multifunctionalgun.items.materials_register.Machine_Item_Register;

public class ItemsRegister {

    public ItemsRegister() {
        new Basic_Materials_Item_Register();
        new Advanced_Materials_Item_Register();
        new Machine_Item_Register();
        new Gun_And_Bullet_Item_Register();
    }
}
