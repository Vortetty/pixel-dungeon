package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.dit599.customPD.R;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.armor.LeatherArmor;
import com.dit599.customPD.items.weapon.Weapon;
import com.dit599.customPD.items.weapon.Weapon.Enchantment;
import com.dit599.customPD.items.weapon.melee.Dagger;
import com.dit599.customPD.levels.template.LevelTemplate;

public class EnchantableItemAdapter extends BaseAdapter {

    private LevelTemplate level;
    private int type;

    Context context;

    private List<Item> activeList = new ArrayList<Item>();

    public EnchantableItemAdapter(Context context, LevelTemplate level, int type) {
        this.context = context;
        this.level = level;
        this.type = type;

        switch (type) {
        case EnchantableItemsActivity.WEAPON:
            activeList = level.weapons;
            break;
        case EnchantableItemsActivity.ARMOR:
            activeList = level.armor;
        }
    }

    @Override
    public int getCount() {
        return activeList.size();
    }

    public void addItem() {
        switch (type) {
        case EnchantableItemsActivity.WEAPON:
            activeList.add(new Dagger());
            break;
        case EnchantableItemsActivity.ARMOR:
            activeList.add(new LeatherArmor());
            break;
        }

        notifyDataSetChanged();
    }

    @Override
    public Item getItem(int position) {
        return activeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customizable_item, null);
        new ItemViewHolder(view, position);
        return view;
    }

    private class ItemViewHolder {
        public int position;

        public Spinner typeSpn;
        public Spinner levelSpn;
        public Spinner enchantSpn;
        public Button deleteBtn;
        public TextView type_tv;
        public TextView enchant_tv;

        public ItemViewHolder(View root, int position) {
            this.position = position;
            typeSpn = (Spinner) root.findViewById(R.id.item_type_spinner);
            levelSpn = (Spinner) root.findViewById(R.id.level_spinner);
            enchantSpn = (Spinner) root.findViewById(R.id.enchant_spinner);
            deleteBtn = (Button) root.findViewById(R.id.delete_btn);
            enchant_tv = (TextView) root.findViewById(R.id.enchant_tv);
            
            switch (type) {
            case EnchantableItemsActivity.WEAPON:
                Weapon presentWeapon = (Weapon) getItem(position);
                
                final List<String> names = WeaponMapping.getAllNames();
                ArrayAdapter<String> weaponAdapter = new ArrayAdapter<String>(root.getContext(),
                        android.R.layout.simple_spinner_item, names);
                weaponAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                typeSpn.setAdapter(weaponAdapter);
                typeSpn.setSelection(names.indexOf(WeaponMapping.getWeaponName(presentWeapon
                        .getClass())));
                typeSpn.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int typeIndex,
                            long id) {
                        Weapon oldWeapon = (Weapon) getItem(ItemViewHolder.this.position);
                        Weapon newWeapon = null;
                        try {
                            newWeapon = (Weapon) WeaponMapping.getWeaponClass(names.get(typeIndex))
                                    .newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        newWeapon.level = oldWeapon.level;
                        newWeapon.enchantment = oldWeapon.enchantment;
                        activeList.remove(ItemViewHolder.this.position);
                        activeList.add(ItemViewHolder.this.position, newWeapon);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                
                List<String> enchants = EnchantmentsMapping.getAllNames();
                ArrayAdapter<String> enchantmentsAdapter = new ArrayAdapter<String>(
                        root.getContext(), android.R.layout.simple_spinner_item, enchants);
                enchantmentsAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                enchantSpn.setAdapter(enchantmentsAdapter);
                if (presentWeapon.enchantment != null) {
                    int selection = enchants.indexOf(EnchantmentsMapping
                            .getEnchantmentName(presentWeapon.enchantment.getClass()));
                    enchantSpn.setSelection(selection);
                } else {
                    enchantSpn.setSelection(0);
                }

                enchantSpn.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int enchantIndex,
                            long id) {
                        Weapon weapon = (Weapon) getItem(ItemViewHolder.this.position);
                        Class<? extends Enchantment> enchantment = EnchantmentsMapping
                                .getEnchantmentClass(EnchantmentsMapping.getAllNames().get(
                                        enchantIndex));
                        if (enchantment != null) {
                            try {
                                weapon.enchant(enchantment.newInstance());
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        } else {
                            weapon.enchant(null);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                String[] levels = context.getResources().getStringArray(R.array.item_levels);
                ArrayAdapter<String> levelsAdapter = new ArrayAdapter<String>(root.getContext(),
                        android.R.layout.simple_spinner_item, levels);
                levelsAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                levelSpn.setAdapter(levelsAdapter);
                levelSpn.setSelection(levelsAdapter.getPosition(String.valueOf(presentWeapon.level)));
                levelSpn.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int levelIndex,
                            long id) {
                        Weapon weapon = (Weapon) getItem(ItemViewHolder.this.position);
                        int level = Integer.valueOf((String) levelSpn.getSelectedItem());
                        weapon.level = level;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                break;
            case EnchantableItemsActivity.ARMOR:

                break;
            }

            deleteBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    activeList.remove(ItemViewHolder.this.position);
                    notifyDataSetChanged();
                }
            });
        }

    }
}
