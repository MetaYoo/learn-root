
git clone 远程库地址

git remote -v 查看远程库
git remote add origin 远程库地址
git remote add upstream  原始远程库地址

git remote rm upstream 删除原始远程库
git remote rename 原来的名字 新名字

git fetch upstream 更新拉去原始远程库代码

git checkout master  -- 切换到主干

git merge upstream/master  -- 将原始远程库的master主干代码合并到当前分支

git push origin master
