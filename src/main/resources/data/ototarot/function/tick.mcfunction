# Loop
schedule function ototarot:tick 1t

# Enable some scoreboards
execute as @a at @s unless score @s check_first_login <= null null unless score @s check_first_login >= null null run scoreboard players set @s check_first_login 0
execute as @a at @s unless score @s lives_remaining <= null null unless score @s lives_remaining >= null null run scoreboard players set @s lives_remaining 3
execute as @a at @s unless score @s has_received_claimer <= null null unless score @s has_received_claimer >= null null run scoreboard players set @s has_received_claimer 0

# First login function
execute as @a[scores={check_first_login=0}] at @s run function ototarot:first_time_login

# Trigger function on death
execute as @a[scores={death_trigger=1}] at @s run function ototarot:remove_live

# Function for giving claimer
execute as @a[scores={has_received_claimer=0},team=Aqua1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Black1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Blue1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Dark_Aqua1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Dark_Blue1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Dark_Gray1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Dark_Green1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Dark_Purple1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Dark_Red1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Gold1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Gray1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Green1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Light_Purple1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Red1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=White1] at @s run function ototarot:give_claimer
execute as @a[scores={has_received_claimer=0},team=Yellow1] at @s run function ototarot:give_claimer