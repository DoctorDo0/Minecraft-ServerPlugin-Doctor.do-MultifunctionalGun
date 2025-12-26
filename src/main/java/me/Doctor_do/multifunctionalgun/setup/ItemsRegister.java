package me.Doctor_do.multifunctionalgun.setup;

import me.Doctor_do.multifunctionalgun.setup.item_register.materials_register.Advanced_Materials_Item_Register;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials_register.Basic_Materials_Item_Register;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials_register.Gun_And_Bullet_Item_Register;
import me.Doctor_do.multifunctionalgun.setup.item_register.materials_register.Machine_Item_Register;

public class ItemsRegister {

    public ItemsRegister() {
        Basic_Materials_Item_Register.Basic_Materials_Item_Register_Setup();
        Advanced_Materials_Item_Register.Advanced_Materials_Item_Register_Setup();
        Machine_Item_Register.Machine_Item_Register_Setup();
        Gun_And_Bullet_Item_Register.Gun_And_Bullet_Item_Register_setup();
    }
}
