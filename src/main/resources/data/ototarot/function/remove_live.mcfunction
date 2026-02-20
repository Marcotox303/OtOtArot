# Stop loops
scoreboard players set @s death_trigger 0

# Removes a live
scoreboard players remove @s lives_remaining 1

# Display "u died" thing
tellraw @s [{"text":"Bro moriste puto tonto jaja"}]
tellraw @s [{"text":"Te quedan "},{"score":{"name":"@s","objective":"lives_remaining"}},{"text":" vidas btw"}]

# When you actually die
execute if score @s lives_remaining <= dead_person lives_remaining run function ototarot:permadeath