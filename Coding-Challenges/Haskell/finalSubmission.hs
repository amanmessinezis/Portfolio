import Data.Map (Map)
import qualified Data.Map as Map
import Data.List (sort)

-- Takes a list of (Int,String) and returns the pair with one string that only has one number
findUnique :: [(Int,String)] -> [(Int,String)]
findUnique pairs = concatMap processPair $ Map.toList pairMap
  where
    pairMap = foldr insertPair Map.empty pairs -- see how this will look like
    insertPair (n, fruit) = Map.insertWith (++) n [fruit]
    processPair (n, fruits) = if length fruits == 1 then [(n, head fruits)] else []

-- Removes the unique pairs from the main list
removeUnique :: [(Int,String)] -> [(Int,String)] -> [(Int,String)]
removeUnique uniquePairs pairs = filter (not . (`elem` uniqueStrings) . snd) pairs
  where
    uniqueStrings = map snd uniquePairs

-- Repeatedly calls findUnique and then removeUnique until no more unique pairings can be found (good)
solveHelper :: [(Int,String)] -> [(Int,String)] -> [(Int,String)]
solveHelper pairs solved
  | null uniquePairs = solved
  | otherwise = solveHelper (removeUnique uniquePairs pairs) (solved ++ uniquePairs)
  where
    uniquePairs = findUnique pairs

-- Sorts the solveHelper result
solve :: [(Int,String)] -> [(Int,String)]
solve pairs = sort $ solveHelper pairs []