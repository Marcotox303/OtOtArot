# Stop loops
scoreboard players add @s check_first_login 1

# Show title
tellraw @s [{"text":"Texto temporal hasta que sepamos que poner aqui jaja"}]
tellraw @s [{"text":"Toma el claimer rey"}]

# Give items
give @s ototarot:terrain_claimer
give @s ototarot:terrain_inspector

#Gives lives
scoreboard players set @s lives_remaining 3