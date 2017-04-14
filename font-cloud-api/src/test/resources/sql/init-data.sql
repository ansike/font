-- init table t_app
insert into t_app(developer_id, app_name, app_key, app_secret, max_font_num) values(1, 'xieyi_test_app1', 'yewbergg', 'hhdshhds', 20);

-- init table t_font
insert into t_font(font_id, code, name, introduction, author, curr_font_version_id, status) values(999999, 'xieyitest', 'xieyi', 'xieyi', 'xieyi', 1, 0);

-- intit table t_font_version
insert into t_font_version(font_version_id, font_id,version_name,ttf_download_url,ttf_size,status) values(999999,999999,'1.1','/xieyi/xieyi.ttf', 1200,0);

-- inti table t_font_version_pic
insert into t_font_version_pic(font_version_id,font_id,pic_type,pic_url,width,height) values(999999,999999,1,'/xieyi/84884.jpg', 200, 200);