---
--- Generated by EmmyLua(https://github.com/EmmyLua)
--- Created by 王超.
--- DateTime: 2019-03-05 10:34
---
local key = "redis.limit." .. KEYS[1]
local limit = tonumber(ARGV[1])
local result = tonumber(redis.call('GET', key) or '0')

if tonumber(result + 1) > limit then
    return false
else
    redis.call("INCRBY", key, 1)
    redis.call("EXPIRE", key, '2')
end
return true