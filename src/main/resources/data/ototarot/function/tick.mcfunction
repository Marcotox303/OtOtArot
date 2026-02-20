# Loop
schedule function ototarot:tick 1t

# Enable some scoreboards
execute as @a at @s unless score @s check_first_login <= null null unless score @s check_first_login >= null null run scoreboard players set @s check_first_login 0
execute as @a at @s unless score @s lives_remaining <= null null unless score @s lives_remaining >= null null run scoreboard players set @s lives_remaining 3

# First login function
execute as @a[scores={check_first_login=0}] at @s run function ototarot:first_time_login

# Trigger function on death
execute as @a[scores={death_trigger=1}] at @s run function ototarot:remove_live