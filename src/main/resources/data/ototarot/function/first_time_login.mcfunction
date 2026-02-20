# Stop loops
scoreboard players add @s check_first_login 1

# Show title
tellraw @s [{"text":"Texto temporal hasta que sepamos que poner aqui jaja"}]
tellraw @s [{"text":"Toma un barco de acacia rey"}]

# Give items
give @s acacia_boat

#Gives lives
scoreboard players set @s lives_remaining 3